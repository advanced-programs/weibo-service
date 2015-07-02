package zx.soft.weibo.mapred.sina.uids;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.redis.client.cache.Cache;
import zx.soft.redis.client.cache.CacheFactory;
import zx.soft.utils.http.ClientDao;
import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.retry.RetryHandler;
import zx.soft.utils.threads.ApplyThreadPool;
import zx.soft.weibo.mapred.hdfs.HdfsWriter;
import zx.soft.weibo.mapred.hdfs.HdfsWriterSimpleImpl;

public class SpiderSinaUidsMain {

	private static Logger logger = LoggerFactory.getLogger(SpiderSinaUidsMain.class);

	public static void main(String[] args) throws IOException, InterruptedException {
		long count = Long.MAX_VALUE;
		if (args.length >= 1) {
			count = Long.valueOf(args[0]);
			logger.info("Spider count: " + count);
		}

		Cache cache = CacheFactory.getInstance();
		if (args.length >= 2) {
			String seedUid = args[1];
			logger.info("Add seed uid: " + seedUid);
			cache.sadd(Spider.WAIT_USERS_KEY, seedUid);
		}

		// 暂时不开放TSDB统计功能
		//		TsdbReporter reporter = new TsdbReporter(Constant.getTsdbHost(), Constant.getTsdbPort());
		//		reporter.addReport(new GatherQueueReport(cache));

		ClientDao clientDao = new HttpClientDaoImpl();
		SinaRelationshipDao dao = getSinaRelationshipDao(clientDao);

		final int cpuNum = 32;
		final ThreadPoolExecutor pool = ApplyThreadPool.getThreadPoolExector(cpuNum);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				pool.shutdown();
			}
		}));

		try (HdfsWriter writer = new HdfsWriterSimpleImpl(Constant.getSinaUserFriendsPath());) {
			while (count-- > 0 && !pool.isShutdown()) {
				String uid = cache.spop(Spider.WAIT_USERS_KEY);
				if (uid != null) {
					try {
						pool.execute(new Spider(uid, cache, writer, dao));
					} catch (IllegalArgumentException e) {
						logger.warn("illegal argumentException, uid={}", uid);
					} catch (Exception e) {
						logger.error("Thread exception: " + Thread.currentThread().getName(), e);
						break;
					}
				} else if (pool.getActiveCount() == 0) {
					logger.info("WaitUsers queue is empty, exit...");
					break;
				}
				// 由于请求次数过多会达到上线，所以休息0.5秒
				Thread.sleep(500);
			}
			pool.shutdown();
			pool.awaitTermination(30, TimeUnit.SECONDS);

		}

		ApplyThreadPool.stop(cpuNum);

	}

	private static SinaRelationshipDao getSinaRelationshipDao(ClientDao clientDao) {
		return (SinaRelationshipDao) Proxy.newProxyInstance(SinaRelationshipDao.class.getClassLoader(),
				new Class[] { SinaRelationshipDao.class }, new RetryHandler<SinaRelationshipDao>(
						new SinaRelationshipDaoImpl(clientDao), 5000, 10) {
					@Override
					protected boolean isRetry(Throwable e) {
						Throwable cause = e.getCause();
						return cause instanceof Exception;
					}
				});
	}

}

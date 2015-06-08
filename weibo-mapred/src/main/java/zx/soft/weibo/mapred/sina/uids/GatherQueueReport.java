package zx.soft.weibo.mapred.sina.uids;

import java.util.ArrayList;
import java.util.List;

import zx.soft.redis.client.cache.Cache;
import zx.soft.weibo.mapred.tsdb.Reportable;
import zx.soft.weibo.mapred.tsdb.Tsdb;

public class GatherQueueReport implements Reportable {

	private final Cache cache;

	public GatherQueueReport(final Cache cache) {
		this.cache = cache;
	}

	@Override
	public List<Tsdb> report() {
		long processed = cache.scard(Spider.PROCESSED_USERS_KEY);
		long wait = cache.scard(Spider.WAIT_USERS_KEY);
		List<Tsdb> result = new ArrayList<Tsdb>();
		result.add(new Tsdb("gather.sina.friendship", processed, "type", "processed"));
		result.add(new Tsdb("gather.sina.friendship", wait, "type", "wait"));
		return result;
	}

}

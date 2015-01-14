package zx.soft.weibo.core.weibos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.http.ClientDao;
import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.log.LogbackUtil;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 新浪公共微博
 * 
 * @author wanggang
 *
 */
public class SinaPublicWeibos {

	private static Logger logger = LoggerFactory.getLogger(SinaPublicWeibos.class);

	private final SinaWeiboAPI api;

	public SinaPublicWeibos(ClientDao clientDao) {
		api = new SinaWeiboAPI(clientDao);
	}

	/**
	 * 测试函数
	 */
	public static void main(String[] args) {
		SinaPublicWeibos publicWeibos = new SinaPublicWeibos(new HttpClientDaoImpl());
		SinaDomain weibos;
		for (int i = 0; i < 100; i++) {
			weibos = publicWeibos.getPublicWeibos(200);
			System.err.println(i);
			System.err.println(weibos.getFieldValues("statuses").size());
		}
		//		SinaDomain weibo;
		//		for (Object status : weibos.getFieldValues("statuses")) {
		//			weibo = (SinaDomain) status;
		//			System.out.println(JsonUtils.toJsonWithoutPretty(weibo));
		//		}
	}

	/**
	 * 获取用户的全部微博数据
	 */
	public SinaDomain getPublicWeibos(int count) {
		try {
			return api.statusesPublicTimeline(count);
		} catch (Exception e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			return null;
		}
	}

}

package zx.soft.weibo.core.weibos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.http.ClientDao;
import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 新浪用户微博列表类
 * 
 * @author wgybzb
 *
 */
public class SinaUserWeibos {

	private static Logger logger = LoggerFactory.getLogger(SinaUserWeibos.class);

	private final SinaWeiboAPI api;

	public SinaUserWeibos(ClientDao clientDao) {
		api = new SinaWeiboAPI(clientDao);
	}

	/**
	 * 测试函数
	 */
	public static void main(String[] args) {
		SinaUserWeibos userWeibos = new SinaUserWeibos(new HttpClientDaoImpl());
		userWeibos.userWeibosToDB("1649155730");
	}

	/**
	 * 获取用户的全部微博数据
	 */
	public void userWeibosToDB(String uid) {
		int page = 1;
		SinaDomain weibos;
		logger.info("Retriving user:{}'s weibos", uid);
		do {
			weibos = api.statusesUserTimelineByUid(uid, "0", "0", 100, page, 0, 0, 0);
			logger.info("User:{}'s weibos at page:{}.", uid, page);
			System.out.println(weibos.getFieldValues("statuses").size());
			page++;
		} while (weibos.getFieldValues("statuses").size() > 1);
	}

}

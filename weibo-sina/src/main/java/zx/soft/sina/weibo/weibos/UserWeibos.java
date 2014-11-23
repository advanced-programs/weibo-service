package zx.soft.sina.weibo.weibos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sina.weibo.api.SinaWeiboAPI;
import zx.soft.sina.weibo.domain.SinaDomain;
import zx.soft.utils.http.ClientDao;
import zx.soft.utils.http.HttpClientDaoImpl;

/**
 * 新浪用户微博列表类
 * 
 * @author wgybzb
 *
 */
public class UserWeibos {

	private static Logger logger = LoggerFactory.getLogger(UserWeibos.class);

	private final SinaWeiboAPI api;

	public UserWeibos(ClientDao clientDao) {
		api = new SinaWeiboAPI(clientDao);
	}

	/**
	 * 测试函数
	 */
	public static void main(String[] args) {
		UserWeibos userWeibos = new UserWeibos(new HttpClientDaoImpl());
		userWeibos.userWeibosToDB("1732243641");
	}

	/**
	 * 获取用户的全部微博数据
	 */
	public void userWeibosToDB(String uid) {
		logger.info("Retriving user:{}'s weibos", uid);
		int page = 1;
		SinaDomain weibos;
		do {
			weibos = api.statusesUserTimelineByUid(uid, "0", "0", 100, page, 0, 0, 0);
			logger.info("User:{}'s weibos at page:{}.", uid, page);
			System.out.println(weibos.getFieldValues("statuses").size());
			page++;
		} while (weibos.getFieldValues("statuses").size() > 1);
	}

}

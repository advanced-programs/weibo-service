package zx.soft.weibo.mapred.sina.uids;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.http.ClientDao;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

@SuppressWarnings("unchecked")
public class SinaRelationshipDaoImpl implements SinaRelationshipDao {

	private static final Logger logger = LoggerFactory.getLogger(SinaRelationshipDaoImpl.class);

	private static final int MAX_COUNT = 5000;

	// 认证失败，也就是Cookie失效
	private static final String ERROR_CODE_EXPIRED_COOKIE = "21301";

	// 官方未说明该错误，不过应该是token无效
	private static final String ERROR_CODE_21332 = "21332";

	SinaWeiboAPI api;

	public SinaRelationshipDaoImpl(ClientDao clientDao) {
		this.api = new SinaWeiboAPI(clientDao);
	}

	@Override
	public String[] getFollowersIds(String uid) {
		String cookie = CookieAccess.getCookie();
		List<String> result = null;
		SinaDomain sinaDomain = null;
		try {
			sinaDomain = api.friendshipsFollowersIDs(uid, MAX_COUNT, 0, cookie);
			result = (List<String>) sinaDomain.get("ids");
			if (result.size() == 0) {
				logger.info("Followers is empyt, uid: " + uid);
			}
			return result.toArray(new String[0]);
		} catch (Exception e) {
			if (isRetry(sinaDomain.get("error_code").toString(), cookie)) {
				return getFollowersIds(uid);
			}
			throw new RuntimeException(String.format("WeiboException: %d\t%s\t%s", sinaDomain.get("error_code")
					.toString(), uid, cookie), e);
		}
	}

	@Override
	public String[] getFriendsIds(String uid) {
		String cookie = CookieAccess.getCookie();
		List<String> result = null;
		SinaDomain sinaDomain = null;
		try {
			sinaDomain = api.friendshipsFriendsIDs(uid, MAX_COUNT, 0, cookie);
			result = (List<String>) sinaDomain.get("ids");
			if (result.size() == 0) {
				logger.info("Friends is empyt, uid: " + uid);
			}
			return result.toArray(new String[0]);
		} catch (Exception e) {
			if (isRetry(sinaDomain.get("error_code").toString(), cookie)) {
				return getFriendsIds(uid);
			}
			throw new RuntimeException(String.format("WeiboException: %d\t%s\t%s", sinaDomain.get("error_code")
					.toString(), uid, cookie), e);
		}
	}

	private boolean isRetry(String error_code, String token) {
		switch (error_code) {
		case ERROR_CODE_EXPIRED_COOKIE:
			try {
				logger.info("weibo API: Cookie is expired.");
				// 休眠一段时间，以降低请求频率
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e1) {
				// Ignore
			}
			return true;
		case ERROR_CODE_21332:
			return true;
		default:
			return false;
		}
	}

}

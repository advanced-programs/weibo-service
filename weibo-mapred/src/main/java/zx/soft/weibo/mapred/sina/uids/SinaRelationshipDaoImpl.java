package zx.soft.weibo.mapred.sina.uids;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.http.ClientDao;
import zx.soft.weibo.mapred.domain.UsersAndIds;
import zx.soft.weibo.mapred.sina.FriendshipsDetail;
import zx.soft.weibo.sina.api.SinaWeiboAPI;

public class SinaRelationshipDaoImpl implements SinaRelationshipDao {

	private static final Logger logger = LoggerFactory.getLogger(SinaRelationshipDaoImpl.class);

	SinaWeiboAPI api;

	public SinaRelationshipDaoImpl(ClientDao clientDao) {
		this.api = new SinaWeiboAPI(clientDao);
	}

	@Override
	public UsersAndIds getFollowers(String uid) {
		try {
			return FriendshipsDetail.getFriendships(api, uid, Boolean.TRUE);
		} catch (Exception e) {
			logger.error("Error followers uid:{}.", uid);
			return null;
		}
	}

	@Override
	public UsersAndIds getFriends(String uid) {
		try {
			return FriendshipsDetail.getFriendships(api, uid, Boolean.FALSE);
		} catch (Exception e) {
			logger.error("Error friends uid:{}.", uid);
			return null;
		}
	}

}

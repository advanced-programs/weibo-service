package zx.soft.weibo.mapred.sina.uids;

import zx.soft.weibo.mapred.domain.UsersAndIds;

public interface SinaRelationshipDao {

	UsersAndIds getFollowers(String uid);

	UsersAndIds getFriends(String uid);

}

package zx.soft.weibo.mapred.sina.uids;

public interface SinaRelationshipDao {

	String[] getFollowersIds(String uid);

	String[] getFriendsIds(String uid);

}

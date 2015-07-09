package zx.soft.weibo.mapred.demo;

import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.mapred.domain.UsersAndIds;
import zx.soft.weibo.mapred.utils.SinaDomainUtils;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

public class UserFollowers {

	public static void main(String[] args) {

		SinaWeiboAPI api = new SinaWeiboAPI(new HttpClientDaoImpl());
		SinaDomain sinaDomain = api.friendshipsFollowers("1642591402", 10, 0, 1);
		UsersAndIds usersAndIds = SinaDomainUtils.getUsersAndIds(sinaDomain);
		System.out.println(JsonUtils.toJson(usersAndIds.getUsers()));

	}

}

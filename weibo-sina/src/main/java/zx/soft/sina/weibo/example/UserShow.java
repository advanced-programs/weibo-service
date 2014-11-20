package zx.soft.sina.weibo.example;

import zx.soft.sina.weibo.api.SinaWeiboAPI;
import zx.soft.sina.weibo.domain.SinaDomain;
import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;

/**
 * 获取用户基本信息
 * 
 * @author wanggang
 *
 */
public class UserShow {

	public static void main(String[] args) {

		SinaWeiboAPI api = new SinaWeiboAPI(new HttpClientDaoImpl());
		SinaDomain sinaDomain = api.userShow("1732243641");
		System.out.println(JsonUtils.toJson(sinaDomain));

	}

}

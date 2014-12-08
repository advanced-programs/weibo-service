package zx.soft.weibo.sina.example;

import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

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

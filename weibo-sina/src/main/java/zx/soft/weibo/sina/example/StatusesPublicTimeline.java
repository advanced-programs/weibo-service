package zx.soft.weibo.sina.example;

import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 返回最新的200条公共微博，返回结果非完全实时
 * 
 * @author wanggang
 *
 */
public class StatusesPublicTimeline {

	public static void main(String[] args) {

		SinaWeiboAPI api = new SinaWeiboAPI(new HttpClientDaoImpl());
		SinaDomain sinaDomain = api.statusesPublicTimeline(2);
		System.out.println(JsonUtils.toJson(sinaDomain));

	}

}

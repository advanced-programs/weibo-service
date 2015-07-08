package zx.soft.weibo.sina.example;

import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 获取某个用户最新发表的微博列表
 * 注意：需要使用特定的superid
 *
 * @author wanggang
 *
 */
public class StatusesUserTimeline {

	public static void main(String[] args) {

		SinaWeiboAPI api = new SinaWeiboAPI(new HttpClientDaoImpl());
		//		SinaDomain sinaDomain = api.statusesUserTimelineByUid("1732243641", "0", "0", 20, 1, 0, 0, 0);
		SinaDomain sinaDomain = api.statusesUserTimelineByScreenName("付强bber", "0", "0", 20, 1, 0, 0, 0);
		System.out.println(JsonUtils.toJson(sinaDomain));

	}

}

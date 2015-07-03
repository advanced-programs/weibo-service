package zx.soft.weibo.sina.example;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;
import zx.soft.utils.threads.ApplyThreadPool;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

public class FriendshipsFollowers {

	public static void main(String[] args) {

		SinaWeiboAPI api = new SinaWeiboAPI(new HttpClientDaoImpl());
		//		SinaDomain sinaDomain = api.friendshipsFollowers("1642591402", 10, 0, 1);
		//		System.out.println(JsonUtils.toJson(sinaDomain));

		ThreadPoolExecutor pool = ApplyThreadPool.getThreadPoolExector(8);

		for (int i = 0; i < 20000; i++) {
			pool.execute(new FollowersRunnable(api, "1642591402"));
		}

	}

	public static class FollowersRunnable implements Runnable {

		private SinaWeiboAPI api;
		private String uid;

		private static final AtomicInteger COUNT = new AtomicInteger(0);

		public FollowersRunnable(SinaWeiboAPI api, String uid) {
			super();
			this.api = api;
			this.uid = uid;
		}

		@Override
		public void run() {
			System.out.println(COUNT.addAndGet(1));
			SinaDomain sinaDomain = api.friendshipsFollowers(uid, 10, 0, 1);
			System.out.println(JsonUtils.toJsonWithoutPretty(sinaDomain));
			if (sinaDomain.get("error_code") != null) {
				System.out.println(sinaDomain.get("error_code"));
				ApplyThreadPool.stop(8);
			}
		}

	}

}

package zx.soft.weibo.sina.example;

import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

public class FriendshipsFollowersIDs {

	public static void main(String[] args) {

		SinaWeiboAPI api = new SinaWeiboAPI(new HttpClientDaoImpl());
		String cookie = "SINAGLOBAL=1967857952695.3398.1430183667535; _s_tentry=login.sina.com.cn; UOR=,,www.cnblogs.com; SUS=SID-1862087393-1433726838-GZ-0dj8v-b0d92cd2bf3d4de8c77a11edc5c1e21f; SUE=es%3D22fd9e23d2da224ecc9e4788e22d22cf%26ev%3Dv1%26es2%3D2e93f2285279a88038ba1a2b2db9346a%26rs0%3DnLACvQSfdT4vnfSPlxUJz6E9l6fiqhBoBQXmhOYb66r38j%252BUvQcIrDKOEIRzKM0nb0OOKlPEIg4GLqDJoI%252F5%252FbsyPNXJbRMb2zyIEkHnwI0x0cxi6cge55nXDTCPKYmTksX7ayWt3HG8IZVSpKJEcrgDmzUzi4kQUOnC0Z89%252BU4%253D%26rv%3D0; SUP=cv%3D1%26bt%3D1433726838%26et%3D1433813238%26d%3Dc909%26i%3De21f%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D2%26st%3D0%26uid%3D1862087393%26name%3Dwgxzy_1015%2540sina.com%26nick%3D%25E6%25B0%25B8%25E4%25B8%258D%25E6%25AD%25A2%25E6%25AD%25A5%26fmp%3D%26lcp%3D2012-03-12%252000%253A03%253A03; SUB=_2A254cJ8mDeTxGedG7VAR-CnPwj-IHXVbB_furDV8PUNbu9BuLXTwkW8X_Y1d9aay9ttY_bWmckyjyCLzYQ..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhOMc47RCc5Rfeu-vvUZEJ45JpX5Kzt; SUHB=0JvRZiYE8S9NA4; ALF=1465262837; SSOLoginState=1433726838; wvr=6; Apache=6243587974458.933.1433726825631; ULV=1433726825660:3:1:1:6243587974458.933.1433726825631:1430816662844; JSESSIONID=1770191B158C76DC70940AEC3A5BDC8F";
		SinaDomain sinaDomain = api.friendshipsFollowersIDs("2123664205", 500, 0, cookie);
		System.out.println(JsonUtils.toJson(sinaDomain));

	}

}

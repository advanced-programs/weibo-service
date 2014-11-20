package zx.soft.sina.weibo.example;

import zx.soft.utils.http.HttpClientDaoImpl;

public class HttpExample {

	public static void main(String[] args) {
		HttpClientDaoImpl httpClientDaoImpl = new HttpClientDaoImpl();
		String url = "https://api.weibo.com/2/comments/show.json?source=140226478&filter_by_author=0&count=50&max_id=0&id=3764456762795565&page=1&since_id=0";
		String cookie = "SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFkT5yBjUZExKNJDuy2PW445JpX5KMt; UOR=www.chinaz.com,widget.weibo.com,login.sina.com.cn; SINAGLOBAL=4774358005242.229.1404870921222; ULV=1413773333874:51:10:1:2119691141365.4128.1413773333866:1413456152178; ALF=1445309399; wvr=5; SUS=SID-1854067572-1413773402-GZ-yfh2c-a128b79202962112a83158db7a51d952; SUE=es%3D72104da67ccfc14c5560bfc55f6bcff0%26ev%3Dv1%26es2%3D4bdb1977f777eb9b1c59cc2252adbb2b%26rs0%3DL8aHGdXR8ScyLLdLmYeM44%252F6kzvSJGdZGl%252FymLVU%252BO50A2r8tnvfvrT%252Ft7D0n00ibLTzE%252FOHkzftB%252BmEk57HvsMfXed%252F%252FXhko3%252BqjOLvk3jQC7yjbff4Nn5m%252FO2ygZN4Qn5gyVFxVDDLk2QXSxlHqlT1VryqxTEAvfwvcIdw8hM%253D%26rv%3D0; SUP=cv%3D1%26bt%3D1413773402%26et%3D1413859802%26d%3Dc909%26i%3Dd952%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D0%26st%3D0%26uid%3D1854067572%26name%3D18256911213%2540sina.cn%26nick%3D182%252A%252A%252A%252A%252A213%2540sina.cn%26fmp%3D%26lcp%3D; SUB=_2AkMjGPdta8NlrAJZnfwVymzna49H-jyQzv2bAn7uJhIyGxgv7kdVqSUJJjOdUZXvnZWk3VI1y76p_h9iQA..; SSOLoginState=1413773402; _s_tentry=login.sina.com.cn; Apache=2119691141365.4128.1413773333866; JSESSIONID=494DA94A475EAB3D720565F4824030A3";
		System.out.println(httpClientDaoImpl.doGet(url, cookie));

		//		String url = "https://api.weibo.com/2/users/show.json?source=2936099636&uid=1732243641";
		//		RestletClientDaoImpl restletClient = new RestletClientDaoImpl();
		//		String result = restletClient.doGet(url, null);
		//		restletClient.close();
		//		System.out.println(result);
	}

}

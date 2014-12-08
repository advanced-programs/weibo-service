package zx.soft.sina.weibos.domain;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import zx.soft.weibo.sina.domain.RequestURL;

public class RequestURLTest {

	private static RequestURL requestURL;

	@BeforeClass
	public static void prepare() {
		requestURL = new RequestURL.Builder("http://www.baidu.com", "123456789").setParams("p1", "v1")
				.setParams("p2", "v2").build();
	}

	@AfterClass
	public static void cleanup() {
		requestURL = null;
		// 一般不要使用
		System.gc();
	}

	@Test
	public void testGetURL() {
		assertEquals("http://www.baidu.com?source=123456789&p1=v1&p2=v2", requestURL.getURL());
	}

	@Test
	public void testToString() {
		assertEquals("RequestURL:{baseURL=http://www.baidu.com,superid=123456789,p1=v1,p2=v2}", requestURL.toString());
	}

}

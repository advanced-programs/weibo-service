package zx.soft.sina.weibos.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import zx.soft.sina.weibo.common.MidToWid;

public class MidToWidTest {

	@Test
	public void testMidToWid() {

		String url1 = "ztkfl7xaf";
		String url2 = "http://weibo.com/1644395354/ztkfl7xaf"; // 3569893431795783
		String url3 = "http://weibo.com/1644395354/ztkfl7xaf?ref=http%3A%2F%2Fweibo.com%2Fu%2F1862087393%3Fwvr%3D3.6%26lf%3Dreg";
		assertEquals("3569893431795783", MidToWid.mid2wid(url1));
		assertEquals("3569893431795783", MidToWid.mid2wid(url2));
		assertEquals("3569893431795783", MidToWid.mid2wid(url3));
	}

}

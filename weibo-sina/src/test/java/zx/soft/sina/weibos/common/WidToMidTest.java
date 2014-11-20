package zx.soft.sina.weibos.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import zx.soft.sina.weibo.common.WidToMid;

public class WidToMidTest {

	@Test
	public void testWidToMid() {

		// http://weibo.com/1644395354/ztkfl7xaf
		assertEquals("ztkfl7xaf", WidToMid.wid2mid("3569893431795783"));
	}

}

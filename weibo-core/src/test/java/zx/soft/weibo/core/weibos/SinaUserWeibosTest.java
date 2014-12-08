package zx.soft.weibo.core.weibos;

import java.text.ParseException;

import org.junit.Ignore;
import org.junit.Test;

import zx.soft.weibo.core.weibos.SinaUserWeibos.DateStr;

public class SinaUserWeibosTest {

	@Test
	@Ignore
	public void testGetDateStr() throws ParseException {
		String str = "Fri Dec 05 00:00:00 +0800 2014";
		DateStr dateStr = SinaUserWeibos.getDateStr(str);
		System.out.println(dateStr);
	}

}

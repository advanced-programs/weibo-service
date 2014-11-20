package zx.soft.sina.weibos.utils;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

import zx.soft.utils.config.ConfigUtil;

public class ConfigUtilTest {

	@Test
	public void testGetProps() {
		Properties props = ConfigUtil.getProps("super.properties");
		assertTrue(props.getProperty("super").length() > 0);
	}

}

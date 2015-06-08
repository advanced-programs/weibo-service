package zx.soft.weibo.mapred.tsdb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TsdbTest {

	@Test
	public void testCreate() {
		String expected = "put proc.stat.cpu " + System.currentTimeMillis() / 1000 + " 54.2 host=foo type=user";
		assertEquals(expected, new Tsdb("proc.stat.cpu", 54.2, "host", "foo", "type", "user").serialize());
	}

	@Test(expected = RuntimeException.class)
	public void testCreateException() {
		new Tsdb("proc.stat.cpu", 54.2, "host").serialize();
	}

}

package cc.pp.sina.mapred.fans;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SingleSinaFansAnalysisTest {

	@Test
	public void testFansQuality() {

		int[] quality = { 11, 22, 33 };
		int sum = quality[0] + quality[1] + quality[2];
		String result = (float) (quality[1] + quality[2]) / sum + "";
		result = result + "," + (float) quality[2] / sum;
		System.out.println(result);
		assertThat(result, is("0.8333333,0.5"));
	}

}

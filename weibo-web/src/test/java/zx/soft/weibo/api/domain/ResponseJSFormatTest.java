package zx.soft.weibo.api.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.web.domain.ResponseJSFormat;
import zx.soft.weibo.web.domain.ResponseJSFormat.NameDataPair;

public class ResponseJSFormatTest {

	@Test
	public void testResponseJSFormat() {
		ResponseJSFormat responseJSFormat = new ResponseJSFormat();
		for (int i = 0; i < 12; i++) {
			responseJSFormat.setCategories(201101 + i + "");
		}
		NameDataPair nameDataPair1 = new NameDataPair();
		nameDataPair1.setName("increment");
		for (int i = 0; i < 12; i++) {
			nameDataPair1.setData(i + 1);
		}
		NameDataPair nameDataPair2 = new NameDataPair();
		nameDataPair2.setName("increment");
		for (int i = 0; i < 12; i++) {
			nameDataPair2.setData(12 - i);
		}
		responseJSFormat.setSeries(nameDataPair1);
		responseJSFormat.setSeries(nameDataPair2);
		String result = "{\"categories\":[\"201101\",\"201102\",\"201103\",\"201104\",\"201105\",\"201106\",\"201107\",\"201108\",\"201109\",\"201110\",\"201111\",\"201112\"],\"series\":[{\"name\":\"increment\",\"data\":[1,2,3,4,5,6,7,8,9,10,11,12]},{\"name\":\"increment\",\"data\":[12,11,10,9,8,7,6,5,4,3,2,1]}]}";
		assertEquals(result, JsonUtils.toJsonWithoutPretty(responseJSFormat));
	}

}

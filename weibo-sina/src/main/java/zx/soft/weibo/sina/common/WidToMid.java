package zx.soft.weibo.sina.common;

/**
 * 微博ID转换工具
 * @author wgybzb
 *
 */
public class WidToMid {

	public static final String DICT = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String wid2mid(String wid) {

		String str = convert10to62(wid.substring(0, wid.length() - 14))
				+ convert10to62(wid.substring(wid.length() - 14, wid.length() - 7))
				+ convert10to62(wid.substring(wid.length() - 7));
		char temp = str.charAt(0);
		while ('0' == temp) {
			str = str.substring(1);
			temp = str.charAt(0);
		}

		return str;
	}

	private static String convert10to62(String str10) {

		String total = "";
		int str = Integer.parseInt(str10);
		int key;
		while (str > 0) {
			key = str % 62;
			str = str / 62;
			total = DICT.charAt(key) + total;
		}
		for (int i = total.length(); i < 4; i++) {
			total = "0" + total;
		}

		return total;
	}

}

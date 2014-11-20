package zx.soft.sina.weibo.common;

/**
 * Title: 微博ID转换工具
 * @author wanggang
 * @version 1.1
 * @since 2013-05-27
 */
public class MidToWid {

	public static final String DICT = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String mid2wid(String url) {

		String temp = "";
		int start1 = url.indexOf("?");
		if (start1 == -1) {
			int start2 = url.lastIndexOf("/");
			if (start2 == -1) {
				temp = url;
			} else {
				temp = url.substring(start2 + 1);
			}
		} else {
			temp = url.substring(start1 - 9, start1);
		}

		return String.valueOf(DICT.indexOf(temp.charAt(0))) + convert62to10(temp.substring(1, 5))
				+ convert62to10(temp.substring(5, 9));
	}

	private static String convert62to10(String str62) {

		long total = 0;
		for (int i = 0; i < str62.length(); i++) {
			total += (long) Math.pow(62, str62.length() - i - 1) * DICT.indexOf(str62.toCharArray()[i]);
		}
		String temp = String.valueOf(total);
		String result = "";
		if (temp.length() > 7) {
			return "0";
		} else {
			for (int i = 0; i < 7 - temp.length(); i++) {
				result += "0";
			}
			for (int j = 7 - temp.length(); j < 7; j++) {
				result += temp.charAt(j + temp.length() - 7);
			}
		}

		return result;
	}

	/**
	 * 测试函数
	 */
	public static void main(String[] args) {

		System.out.println(MidToWid.mid2wid("http://e.weibo.com/1704103183/AoK1fp7TA"));
	}

}

package zx.soft.weibo.web.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.weibo.web.server.UserInfoServer;

public class WeiboWebDriver {

	private static Logger logger = LoggerFactory.getLogger(WeiboWebDriver.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Usage: Driver <class-name>");
			System.exit(-1);
		}
		String[] leftArgs = new String[args.length - 1];
		System.arraycopy(args, 1, leftArgs, 0, leftArgs.length);
		switch (args[0]) {
		case "userInfoServer":
			logger.info("新浪微博用户信息服务： ");
			UserInfoServer.main(leftArgs);
			break;
		default:
			return;
		}
	}

}

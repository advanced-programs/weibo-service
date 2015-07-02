package zx.soft.weibo.mapred.sina.uids;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constant {

	private static final Logger logger = LoggerFactory.getLogger(Constant.class);

	private static String sinaUserFriendsPath;

	private static String tsdbHost;

	private static int tsdbPort;

	static {
		try (InputStream in = Constant.class.getClassLoader().getResourceAsStream("cache-config.properties")) {
			Properties conf = new Properties();
			conf.load(in);

			sinaUserFriendsPath = conf.getProperty("sina.user.friends.path");
			logger.info("sinaUserFriendsPath: " + sinaUserFriendsPath);

			tsdbHost = conf.getProperty("tsdb.host");
			tsdbPort = Integer.parseInt(conf.getProperty("tsdb.port"));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getSinaUserFriendsPath() {
		return sinaUserFriendsPath;
	}

	public static String getTsdbHost() {
		return tsdbHost;
	}

	public static int getTsdbPort() {
		return tsdbPort;
	}

}

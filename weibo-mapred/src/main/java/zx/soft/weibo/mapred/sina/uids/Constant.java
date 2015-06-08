package zx.soft.weibo.mapred.sina.uids;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constant {

	private static final Logger logger = LoggerFactory.getLogger(Constant.class);

	private static String sinaUserFriendsPath;

	private static String dbUrl;

	private static String dbUser;

	private static String dbPasswd;

	private static String tsdbHost;

	private static int tsdbPort;

	static {
		try (InputStream in = Constant.class.getClassLoader().getResourceAsStream("conf.properties")) {
			Properties conf = new Properties();
			conf.load(in);

			sinaUserFriendsPath = conf.getProperty("sinaUserFriendsPath");
			logger.info("sinaUserFriendsPath: " + sinaUserFriendsPath);

			dbUrl = conf.getProperty("dbUrl");
			logger.info("dbUrl: " + dbUrl);

			dbUser = conf.getProperty("dbUser");
			logger.info("dbUser: " + dbUser);

			dbPasswd = conf.getProperty("dbPasswd");

			tsdbHost = conf.getProperty("tsdb.host");
			tsdbPort = Integer.parseInt(conf.getProperty("tsdb.port"));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getDbPasswd() {
		return dbPasswd;
	}

	public static String getDbUrl() {
		return dbUrl;
	}

	public static String getDbUser() {
		return dbUser;
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

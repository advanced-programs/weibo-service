package zx.soft.weibo.dao.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.config.ConfigUtil;
import zx.soft.utils.log.LogbackUtil;

/**
 * 舆情数据JDBC
 * 
 * @author wanggang
 *
 */
public class WeiboDbcp {

	private static Logger logger = LoggerFactory.getLogger(WeiboDbcp.class);

	private BasicDataSource dataSource;

	private String db_url;
	private String db_username;
	private String db_password;

	public WeiboDbcp() {
		dbInit();
		dbConnection();
	}

	/**
	 * 初始化数据库相关参数
	 */
	private void dbInit() {
		Properties props = ConfigUtil.getProps("data_db.properties");
		db_url = props.getProperty("weibos.db.url");
		db_username = props.getProperty("weibos.db.username");
		db_password = props.getProperty("weibos.db.password");
	}

	/**
	 * 链接数据库
	 */
	private void dbConnection() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(db_url);
		dataSource.setUsername(db_username);
		dataSource.setPassword(db_password);
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("select 1");
	}

	/**
	 * 获取链接
	 */
	private Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			logger.info("Db close error.");
		}
	}

	@Override
	public String toString() {
		return "WeiboDbcp:[db_url=" + db_url + ",db_username=" + db_username + ",db_password=" + db_password + "]";
	}

	/**
	 * 创建新浪用户微博数据表
	 */
	public void createSinaUserWeibosTable(String tablename) {
		String sql = "CREATE TABLE "
				+ tablename
				+ " (`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',"
				+ "`wid` bigint(20) unsigned NOT NULL COMMENT '微博id',"
				+ "`username` bigint(20) unsigned NOT NULL COMMENT '用户id',"
				+ "`repostscount` mediumint(8) unsigned NOT NULL COMMENT '转发数量',"
				+ "`commentscount` mediumint(8) unsigned NOT NULL COMMENT '评论数量',"
				+ "`attitudescount` mediumint(8) unsigned NOT NULL COMMENT '赞数量',"
				+ "`text` varchar(300) NOT NULL COMMENT '微博内容',"
				+ "`createat` datetime NOT NULL COMMENT '微博创建时间',"
				+ "`owid` bigint(20) unsigned NOT NULL,"
				+ "`ousername` bigint(20) unsigned NOT NULL COMMENT '被转发或原创用户d',"
				+ "`favorited` tinyint(1) NOT NULL COMMENT '是否已收藏',"
				+ "`geo` varchar(300) NOT NULL COMMENT '地理信息字段',"
				+ "`latitude` float NOT NULL COMMENT '经度',"
				+ "`longitude` float NOT NULL COMMENT '维度',"
				+ "`originalpic` varchar(500) NOT NULL COMMENT '原始图片地址',"
				+ "`source` varchar(30) NOT NULL COMMENT '微博来源',"
				+ "`visible` varchar(30) NOT NULL COMMENT '微博的可见性及指定可见分组信息',"
				+ "`mlevel` smallint(8) unsigned NOT NULL COMMENT '微博等级',"
				+ "`lasttime` datetime NOT NULL COMMENT '采集时间',"
				+ "PRIMARY KEY (`id`),KEY `wid` (`wid`),KEY `username` (`username`),KEY `owid` (`owid`),KEY `ousername` (`ousername`)) "
				+ "ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='新浪的用户微博信息' AUTO_INCREMENT=1 ;";
		execSQL(sql);
	}

	/**
	 * 创建存放微博数据表表名的数据表
	 */
	public void createTablenamesTable(String tablename) {
		String sql = "CREATE TABLE " + tablename + " (`id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "`name` char(50) NOT NULL,`lasttime` datetime NOT NULL COMMENT '记录时间',"
				+ "PRIMARY KEY (`id`)) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";
		execSQL(sql);
	}

	/**
	 * 执行sql语句
	 */
	private void execSQL(String sql) {
		try (Connection conn = getConnection(); Statement pstmt = conn.createStatement();) {
			pstmt.execute(sql);
		} catch (SQLException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			throw new RuntimeException("SQLException: " + e);
		}
	}

}

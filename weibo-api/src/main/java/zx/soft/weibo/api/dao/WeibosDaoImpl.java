package zx.soft.weibo.api.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.weibo.api.common.MybatisConfig;
import zx.soft.weibo.api.domain.SinaUserBaseInfo;
import zx.soft.weibo.api.domain.TencentUserBaseInfo;
import zx.soft.weibo.api.domain.UserInfosByLocationParams;

public class WeibosDaoImpl {

	private static Logger logger = LoggerFactory.getLogger(WeibosDaoImpl.class);

	private static SqlSessionFactory sqlSessionFactory;

	public WeibosDaoImpl(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("SentimentRecord RuntimeException:{}", e);
		}
	}

	/**
	 * 新浪：获取某个地区的用户总量
	 */
	public int getSinaUsersCountByLocation(String tablename, String province, String city) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao weibosDao = sqlSession.getMapper(WeibosDao.class);
			return weibosDao
					.getSinaUsersCountByLocation(new UserInfosByLocationParams(tablename, province, city, 1, 1));
		}
	}

	/**
	 * 新浪：获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getSinaUserInfosByLocation(String tablename, String province, String city, int start,
			int rows) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao weibosDao = sqlSession.getMapper(WeibosDao.class);
			return weibosDao.getSinaUserInfosByLocation(new UserInfosByLocationParams(tablename, province, city, start,
					rows));
		}
	}

	/**
	 * 腾讯：获取某个地区的用户总量
	 */
	public int getTencentUsersCountByLocation(String tablename, String province, String city) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao weibosDao = sqlSession.getMapper(WeibosDao.class);
			return weibosDao.getTencentUsersCountByLocation(new UserInfosByLocationParams(tablename, province, city, 1,
					1));
		}
	}

	/**
	 * 腾讯：获取某个地区的用户基本信息
	 */
	public List<TencentUserBaseInfo> getTencentUserInfosByLocation(String tablename, String province, String city,
			int start, int rows) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao weibosDao = sqlSession.getMapper(WeibosDao.class);
			return weibosDao.getTencentUserInfosByLocation(new UserInfosByLocationParams(tablename, province, city,
					start, rows));
		}
	}

}

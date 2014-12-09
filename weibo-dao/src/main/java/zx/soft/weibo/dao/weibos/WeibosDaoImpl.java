package zx.soft.weibo.dao.weibos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.weibo.dao.common.MybatisConfig;
import zx.soft.weibo.dao.domain.SinaUserBaseInfo;
import zx.soft.weibo.dao.domain.TencentUserBaseInfo;
import zx.soft.weibo.dao.domain.UserInfosByLocationParams;
import zx.soft.weibo.dao.domain.UserWeibosGroupParams;
import zx.soft.weibo.dao.domain.sina.SimpleWeibosInfo;
import zx.soft.weibo.dao.domain.sina.WeiboDayCount;

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

	/**
	 * 插入新浪用户微博数据
	 */
	public void insertSinaUserWeibos(SimpleWeibosInfo simpleWeibosInfo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao simpleWeibosDao = sqlSession.getMapper(WeibosDao.class);
			simpleWeibosDao.insertSinaUserWeibos(simpleWeibosInfo);
		}
	}

	/**
	 * 获取某个用户当前微博总数
	 */
	public int selectUserWeiboCount(String tablename, String uid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao simpleWeibosDao = sqlSession.getMapper(WeibosDao.class);
			return simpleWeibosDao.selectUserWeiboCount(tablename, uid);
		}
	}

	/**
	 * 按天根据用户名获取分组统计结果（按年/月/日）
	 */
	public List<WeiboDayCount> selectUserWeibosGroupByInterval(String tablename, String uid, String interval, int limitn) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao simpleWeibosDao = sqlSession.getMapper(WeibosDao.class);
			return simpleWeibosDao.selectUserWeibosGroupByInterval(new UserWeibosGroupParams(tablename, uid, interval,
					limitn));
		}
	}

}

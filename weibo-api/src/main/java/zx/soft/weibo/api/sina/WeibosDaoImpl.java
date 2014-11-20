package zx.soft.weibo.api.sina;

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
	 * 新浪：获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getSinaUserInfosByLocation(String tablename, int province, int city, int count) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao sinaWeibosDao = sqlSession.getMapper(WeibosDao.class);
			return sinaWeibosDao.getSinaUserInfosByLocation(new UserInfosByLocationParams(tablename, province, city,
					count));
		}
	}

	/**
	 * 腾讯：获取某个地区的用户基本信息
	 */
	public List<TencentUserBaseInfo> getTencentUserInfosByLocation(String tablename, int province, int city, int count) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			WeibosDao sinaWeibosDao = sqlSession.getMapper(WeibosDao.class);
			return sinaWeibosDao.getTencentUserInfosByLocation(new UserInfosByLocationParams(tablename, province, city,
					count));
		}
	}

}

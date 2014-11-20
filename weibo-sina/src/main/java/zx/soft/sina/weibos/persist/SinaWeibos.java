package zx.soft.sina.weibos.persist;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.sina.weibos.common.MybatisConfig;
import zx.soft.sina.weibos.domain.SinaUserBaseInfo;
import zx.soft.sina.weibos.domain.UserInfosByLocationParams;

public class SinaWeibos {

	private static Logger logger = LoggerFactory.getLogger(SinaWeibos.class);

	private static SqlSessionFactory sqlSessionFactory;

	public SinaWeibos(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("SentimentRecord RuntimeException:{}", e);
		}
	}

	/**
	 * 获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getUserInfosByLocation(String tablename, int province, int city, int count) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao
					.getUserInfosByLocation(new UserInfosByLocationParams(tablename, province, city, count));
		}
	}

}

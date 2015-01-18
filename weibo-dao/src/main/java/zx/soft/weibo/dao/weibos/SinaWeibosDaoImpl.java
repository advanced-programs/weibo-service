package zx.soft.weibo.dao.weibos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.log.LogbackUtil;
import zx.soft.weibo.dao.common.MybatisConfig;
import zx.soft.weibo.dao.domain.sina.TablenameInsertParams;
import zx.soft.weibo.dao.domain.sina.WeiboInfo;
import zx.soft.weibo.dao.domain.sina.WeiboInsertParams;
import zx.soft.weibo.dao.domain.sina.WeiboSelectParams;
import zx.soft.weibo.dao.domain.sina.WeibosSelectParams;

/**
 * 新浪用户微博数据
 * @author wgybzb
 *
 */
public class SinaWeibosDaoImpl {

	private static Logger logger = LoggerFactory.getLogger(SinaWeibosDaoImpl.class);

	private static SqlSessionFactory sqlSessionFactory;

	public SinaWeibosDaoImpl(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
		}
	}

	/**
	 * 获取数据表中的最大bid
	 */
	public int getMaxBid(String tablename) {
		WeibosSelectParams weibosSelectParams = new WeibosSelectParams(tablename, 0, 0);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getMaxBid(weibosSelectParams);
		}
	}

	/**
	 * 插入单条微博数据，需要判断原创微博是否为空
	 */
	public void insertWeiboInfo(WeiboInsertParams weiboInsertParams) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			sinaWeibosDao.insertWeiboInfo(weiboInsertParams);
		}
	}

	/**
	 * 批量获取微博数据
	 */
	public List<WeiboInfo> getWeibosInfo(String tablename, int low, int high) {
		WeibosSelectParams weibosSelectParams = new WeibosSelectParams(tablename, low, high);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getWeibosInfo(weibosSelectParams);
		}
	}

	/**
	 * 获取单条微博数据
	 */
	public WeiboInfo getWeiboInfo(String tablename, long wid) {
		WeiboSelectParams weiboSelectParams = new WeiboSelectParams(tablename, wid);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getWeiboInfo(weiboSelectParams);
		}
	}

	/**
	 * 删除单条微博数据
	 */
	public void deleteWeiboInfo(String tablename, long wid) {
		WeiboSelectParams weiboSelectParams = new WeiboSelectParams(tablename, wid);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			sinaWeibosDao.deleteWeiboInfo(weiboSelectParams);
		}
	}

	/**
	 * 插入微博数据表名
	 */
	public void insertTablenames(String tablename, String name) {
		TablenameInsertParams tablenameInsertParams = new TablenameInsertParams(tablename, name);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			sinaWeibosDao.insertTablenames(tablenameInsertParams);
		}
	}

	/**
	 * 读取微博数据表名的数据表
	 */
	public List<String> getTablenames(String tablename) {
		TablenameInsertParams tablenameInsertParams = new TablenameInsertParams(tablename, "0");
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getTablenames(tablenameInsertParams);
		}
	}

}

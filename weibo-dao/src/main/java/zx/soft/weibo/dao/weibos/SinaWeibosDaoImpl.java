package zx.soft.weibo.dao.weibos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import zx.soft.weibo.dao.common.MybatisConfig;
import zx.soft.weibo.dao.domain.sina.TablenameInsertParams;
import zx.soft.weibo.dao.domain.sina.WeiboInfo;
import zx.soft.weibo.dao.domain.sina.WeiboInfoOld;
import zx.soft.weibo.dao.domain.sina.WeiboInsertParams;
import zx.soft.weibo.dao.domain.sina.WeiboSelectParams;
import zx.soft.weibo.dao.domain.sina.WeibosSelectParams;
import zx.soft.weibo.sina.domain.Status;

/**
 * 新浪用户微博数据
 * @author wgybzb
 *
 */
public class SinaWeibosDaoImpl {

	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(MybatisConfig.ServerEnum.weibos);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取数据表中的最大bid
	 */
	public static int getMaxBid(String tablename) {
		WeibosSelectParams weibosSelectParams = new WeibosSelectParams(tablename, 0, 0);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getMaxBid(weibosSelectParams);
		}
	}

	/**
	 * 插入单条微博数据，需要判断原创微博是否为空
	 */
	public static void insertWeiboInfo(String tablename, Status weibo) {
		WeiboInsertParams weiboInsertParams = new WeiboInsertParams.Builder(tablename, Long.parseLong(weibo.getId()),
				Long.parseLong(weibo.getUser().getId())).setRepostscount(weibo.getRepostsCount())
				.setCommentscount(weibo.getCommentsCount()).setAttitudescount(weibo.getAttitudesCount())
				.setText(weibo.getText()).setCreateat(weibo.getCreatedAt().getTime() / 1000)
				.setOwid(Long.parseLong(weibo.getRetweetedStatus().getId()))
				.setOusername(Long.parseLong(weibo.getRetweetedStatus().getUser().getId()))
				.setFavorited(weibo.isFavorited()).setGeo(weibo.getGeo()).setLatitude(weibo.getLatitude())
				.setLongitude(weibo.getLongitude()).setOriginalpic(weibo.getOriginalPic())
				.setSource(weibo.getSource().getName())
				.setVisible(weibo.getVisible().getList_id() + "," + weibo.getVisible().getType())
				.setMlevel(weibo.getMlevel()).setLasttime(System.currentTimeMillis() / 1000).build();
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			sinaWeibosDao.insertWeiboInfo(weiboInsertParams);
		}
	}

	/**
	 * 批量获取微博数据
	 */
	public static List<WeiboInfo> getWeibosInfo(String tablename, int low, int high) {
		WeibosSelectParams weibosSelectParams = new WeibosSelectParams(tablename, low, high);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getWeibosInfo(weibosSelectParams);
		}
	}

	public static List<WeiboInfoOld> getWeibosInfoOld(String tablename, int low, int high) {
		WeibosSelectParams weibosSelectParams = new WeibosSelectParams(tablename, low, high);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getWeibosInfoOld(weibosSelectParams);
		}
	}

	/**
	 * 获取单条微博数据
	 */
	public static WeiboInfo getWeiboInfo(String tablename, long wid) {
		WeiboSelectParams weiboSelectParams = new WeiboSelectParams(tablename, wid);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getWeiboInfo(weiboSelectParams);
		}
	}

	/**
	 * 删除单条微博数据
	 */
	public static void deleteWeiboInfo(String tablename, long wid) {
		WeiboSelectParams weiboSelectParams = new WeiboSelectParams(tablename, wid);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			sinaWeibosDao.deleteWeiboInfo(weiboSelectParams);
		}
	}

	/**
	 * 插入微博数据表名
	 */
	public static void insertTablenames(String tablename, String name) {
		TablenameInsertParams tablenameInsertParams = new TablenameInsertParams(tablename, name);
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			sinaWeibosDao.insertTablenames(tablenameInsertParams);
		}
	}

	/**
	 * 读取微博数据表名的数据表
	 */
	public static List<String> getTablenames(String tablename) {
		TablenameInsertParams tablenameInsertParams = new TablenameInsertParams(tablename, "0");
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SinaWeibosDao sinaWeibosDao = sqlSession.getMapper(SinaWeibosDao.class);
			return sinaWeibosDao.getTablenames(tablenameInsertParams);
		}
	}

}

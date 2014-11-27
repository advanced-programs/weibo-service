package zx.soft.weibo.dao.weibos;

import java.util.List;

import zx.soft.weibo.dao.domain.sina.TablenameInsertParams;
import zx.soft.weibo.dao.domain.sina.WeiboInfo;
import zx.soft.weibo.dao.domain.sina.WeiboInfoOld;
import zx.soft.weibo.dao.domain.sina.WeiboInsertParams;
import zx.soft.weibo.dao.domain.sina.WeiboSelectParams;
import zx.soft.weibo.dao.domain.sina.WeibosSelectParams;

/**
 * 新浪用户微博数据Dao
 * @author wgybzb
 *
 */
public interface SinaWeibosDao {

	/**
	 * 获取数据表中的最大bid
	 */
	public int getMaxBid(WeibosSelectParams weibosSelectParams);

	/**
	 * 插入单条微博数据
	 */
	public void insertWeiboInfo(WeiboInsertParams weiboInsertParams);

	/**
	 * 批量获取微博数据
	 */
	public List<WeiboInfo> getWeibosInfo(WeibosSelectParams weibosSelectParams);

	public List<WeiboInfoOld> getWeibosInfoOld(WeibosSelectParams weibosSelectParams);

	/**
	 * 获取单条微博数据
	 */
	public WeiboInfo getWeiboInfo(WeiboSelectParams weiboSelectParams);

	/**
	 * 删除单条微博数据
	 */
	public void deleteWeiboInfo(WeiboSelectParams weiboSelectParams);

	/**
	 * 插入微博数据表名
	 */
	public void insertTablenames(TablenameInsertParams tablenameInsertParams);

	/**
	 * 读取微博数据表名的数据表
	 */
	public List<String> getTablenames(TablenameInsertParams tablenameInsertParams);

}

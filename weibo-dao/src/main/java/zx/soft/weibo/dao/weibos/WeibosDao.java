package zx.soft.weibo.dao.weibos;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zx.soft.weibo.dao.domain.SinaUserBaseInfo;
import zx.soft.weibo.dao.domain.TencentUserBaseInfo;
import zx.soft.weibo.dao.domain.UserInfosByLocationParams;
import zx.soft.weibo.dao.domain.sina.SimpleWeibosInfo;
import zx.soft.weibo.dao.domain.sina.WeiboDayCount;

/**
 * 持久化到MySQL的接口
 * 
 * @author wanggang
 *
 */
public interface WeibosDao {

	/**
	 * 新浪：获取某个地区的用户总量
	 */
	public int getSinaUsersCountByLocation(UserInfosByLocationParams userInfosByLocationParams);

	/**
	 * 新浪：获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getSinaUserInfosByLocation(UserInfosByLocationParams userInfosByLocationParams);

	/**
	 * 腾讯：获取某个地区的用户总量
	 */
	public int getTencentUsersCountByLocation(UserInfosByLocationParams userInfosByLocationParams);

	/**
	 * 腾讯：获取某个地区的用户基本信息
	 */
	public List<TencentUserBaseInfo> getTencentUserInfosByLocation(UserInfosByLocationParams userInfosByLocationParams);

	/**
	 * 插入新浪用户微博数据
	 */
	public void insertSinaUserWeibos(SimpleWeibosInfo simpleWeibosInfo);

	/**
	 * 获取某个用户当前微博总数
	 */
	@Select("SELECT statusescount FROM `sina_user_weibos` WHERE uid = #{uid} LIMIT 1")
	public int selectSinaUserWeiboCount(@Param("uid") String uid);

	/**
	 * 按天根据用户名获取每天分组统计结果
	 */
	public List<WeiboDayCount> selectSinaUserWeibosGroupByDay(String uid);

	/**
	 * 按天根据用户名获取每月分组统计结果
	 */
	public List<WeiboDayCount> selectSinaUserWeibosGroupByMonth(String uid);

}

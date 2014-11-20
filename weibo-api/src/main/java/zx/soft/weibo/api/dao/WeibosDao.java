package zx.soft.weibo.api.dao;

import java.util.List;

import zx.soft.weibo.api.domain.SinaUserBaseInfo;
import zx.soft.weibo.api.domain.TencentUserBaseInfo;
import zx.soft.weibo.api.domain.UserInfosByLocationParams;

/**
 * 持久化到MySQL的接口
 * 
 * @author wanggang
 *
 */
public interface WeibosDao {

	/**
	 * 新浪：获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getSinaUserInfosByLocation(UserInfosByLocationParams userInfosByLocationParams);

	/**
	 * 腾讯：获取某个地区的用户基本信息
	 */
	public List<TencentUserBaseInfo> getTencentUserInfosByLocation(UserInfosByLocationParams userInfosByLocationParams);

}

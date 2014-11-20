package zx.soft.sina.weibos.persist;

import java.util.List;

import zx.soft.sina.weibos.domain.SinaUserBaseInfo;
import zx.soft.sina.weibos.domain.UserInfosByLocationParams;

/**
 * 持久化到MySQL的接口
 * 
 * @author wanggang
 *
 */
public interface SinaWeibosDao {

	/**
	 * 获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getUserInfosByLocation(UserInfosByLocationParams userInfosByLocationParams);

}

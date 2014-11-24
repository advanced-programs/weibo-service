package zx.soft.weibo.api.application;

import java.util.List;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import zx.soft.weibo.api.common.MybatisConfig;
import zx.soft.weibo.api.dao.WeibosDaoImpl;
import zx.soft.weibo.api.domain.SinaUserBaseInfo;
import zx.soft.weibo.api.domain.TencentUserBaseInfo;
import zx.soft.weibo.api.domain.UserWeibosGroup;
import zx.soft.weibo.api.resource.UserBaseInfoResource;
import zx.soft.weibo.api.resource.UserWeibosInfoResource;

public class UserInfoApplication extends Application {

	private final WeibosDaoImpl weibosDaoImpl;

	public UserInfoApplication() {
		weibosDaoImpl = new WeibosDaoImpl(MybatisConfig.ServerEnum.weibos);
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/{type}/users/{province}/{city}", UserBaseInfoResource.class);
		router.attach("/{type}/users/weibos/{uid}/group", UserWeibosInfoResource.class);
		return router;
	}

	/**
	 * 新浪：获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getSinaUserInfosByLocation(String tablename, String province, String city, int start,
			int rows) {
		return weibosDaoImpl.getSinaUserInfosByLocation(tablename, province, city, start, rows);
	}

	/**
	 * 腾讯：获取某个地区的用户基本信息
	 */
	public List<TencentUserBaseInfo> getTencentUserInfosByLocation(String tablename, String province, String city,
			int start, int rows) {
		return weibosDaoImpl.getTencentUserInfosByLocation(tablename, province, city, start, rows);
	}

	/**
	 * 新浪：根据时间段统计用户的发博数量
	 */
	public UserWeibosGroup analysisSinaUserWeibosByInterval(String tablename, String uid) {
		return new UserWeibosGroup().instance();
	}

	/**
	 * 腾讯：根据时间段统计用户的发博数量
	 */
	public UserWeibosGroup analysisTencentUserWeibosByInterval(String tablename, String uid) {
		return new UserWeibosGroup().instance();
	}

	public void close() {
		//
	}

}

package zx.soft.weibo.api.application;

import java.util.List;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import zx.soft.weibo.api.common.MybatisConfig;
import zx.soft.weibo.api.domain.SinaUserBaseInfo;
import zx.soft.weibo.api.domain.TencentUserBaseInfo;
import zx.soft.weibo.api.resource.UserInfoResource;
import zx.soft.weibo.api.sina.WeibosDaoImpl;

public class UserInfoApplication extends Application {

	private final WeibosDaoImpl sinaWeibos;

	public UserInfoApplication() {
		sinaWeibos = new WeibosDaoImpl(MybatisConfig.ServerEnum.weibos);
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/{type}/users/{province}/{city}", UserInfoResource.class);
		return router;
	}

	/**
	 * 新浪：获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getSinaUserInfosByLocation(String tablename, int province, int city, int count) {
		return sinaWeibos.getSinaUserInfosByLocation(tablename, province, city, count);
	}

	/**
	 * 腾讯：获取某个地区的用户基本信息
	 */
	public List<TencentUserBaseInfo> getTencentUserInfosByLocation(String tablename, int province, int city, int count) {
		return sinaWeibos.getTencentUserInfosByLocation(tablename, province, city, count);
	}

	public void close() {
		//
	}

}

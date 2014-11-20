package zx.soft.weibo.api.application;

import java.util.List;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import zx.soft.weibo.api.common.MybatisConfig;
import zx.soft.weibo.api.domain.SinaUserBaseInfo;
import zx.soft.weibo.api.resource.UserInfoResource;
import zx.soft.weibo.api.sina.SinaWeibos;

public class UserInfoApplication extends Application {

	private final SinaWeibos sinaWeibos;

	public UserInfoApplication() {
		sinaWeibos = new SinaWeibos(MybatisConfig.ServerEnum.weibos);
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/users/{province}/{city}", UserInfoResource.class);
		return router;
	}

	/**
	 * 获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getUserInfosByLocation(String tablename, int province, int city, int count) {
		return sinaWeibos.getUserInfosByLocation(tablename, province, city, count);
	}

	public void close() {
		//
	}

}

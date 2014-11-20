package zx.soft.weibo.api.server;

import java.util.Properties;

import org.restlet.Component;
import org.restlet.data.Protocol;

import zx.soft.utils.config.ConfigUtil;
import zx.soft.weibo.api.application.UserInfoApplication;

/**
 * 新浪用户信息服务
 * 1、GET基本信息
 *    http://localhost:8100/weibos/{type}/users/{uid}   示例：sina/1732243641
 * 2、根据区域查询用户信息
 *    http://localhost:8100/weibos/{type}/users/{province}/{city} 
 * 3、按时间段统计用户的微博量
 *    增量和全量统计：
 *    http://localhost:8100/weibos/{type}/users/weibos/{uid}/group    
 *    
 * @author wgybzb
 *
 */
public class UserInfoServer {

	private final Component component;
	private final UserInfoApplication userInfoApplication;
	private final int PORT;

	public UserInfoServer() {
		Properties props = ConfigUtil.getProps("web-server.properties");
		PORT = Integer.parseInt(props.getProperty("api.port"));
		component = new Component();
		userInfoApplication = new UserInfoApplication();
	}

	/**
	 * 主函数
	 */
	public static void main(String[] args) {
		UserInfoServer server = new UserInfoServer();
		server.start();
	}

	public void start() {
		component.getServers().add(Protocol.HTTP, PORT);
		try {
			component.getDefaultHost().attach("/weibos", userInfoApplication);
			component.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			component.stop();
			userInfoApplication.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

package zx.soft.weibo.api.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.chars.JavaPattern;
import zx.soft.utils.codec.URLCodecUtils;
import zx.soft.weibo.api.application.UserInfoApplication;
import zx.soft.weibo.api.domain.ErrorResponse;

public class UserWeibosInfoResource extends ServerResource {

	private static Logger logger = LoggerFactory.getLogger(UserWeibosInfoResource.class);

	private UserInfoApplication application;

	private String type = "";
	private String uid = "";

	@Override
	public void doInit() {
		application = (UserInfoApplication) getApplication();
		type = (String) this.getRequest().getAttributes().get("type");
		uid = (String) this.getRequest().getAttributes().get("uid");
		logger.info("Request Url: " + URLCodecUtils.decoder(getReference().toString(), "utf-8") + ".");
	}

	@Get("json")
	public Object retriveUserInfo() {
		if (type == null || type.length() == 0 || uid == null || uid.length() == 0) {
			logger.error("Params `type` or `uid` is null.");
			return new ErrorResponse.Builder(-1, "params error!").build();
		}
		if ("sina".equalsIgnoreCase(type)) {
			if (JavaPattern.isAllNum(uid)) {
				return application.analysisSinaUserWeibosByInterval("sina_user_weibos", uid);
			} else {
				logger.error("Params `type` or `uid` is null.");
				return new ErrorResponse.Builder(-1, "param `uid` is error!").build();
			}
		} else if ("tencent".equalsIgnoreCase(type)) {
			return application.analysisTencentUserWeibosByInterval("tencent_user_weibos", uid);
		} else {
			return new ErrorResponse.Builder(-1, "param `type` is error!").build();
		}
	}

}

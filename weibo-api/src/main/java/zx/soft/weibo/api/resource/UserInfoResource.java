package zx.soft.weibo.api.resource;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.chars.JavaPattern;
import zx.soft.utils.codec.URLCodecUtils;
import zx.soft.weibo.api.application.UserInfoApplication;
import zx.soft.weibo.api.domain.ErrorResponse;
import zx.soft.weibo.api.domain.SinaUserBaseInfo;

public class UserInfoResource extends ServerResource {

	private static Logger logger = LoggerFactory.getLogger(UserInfoResource.class);

	private UserInfoApplication application;

	private String province = "";
	private String city = "";

	@Override
	public void doInit() {
		application = (UserInfoApplication) getApplication();
		province = (String) this.getRequest().getAttributes().get("province");
		city = (String) this.getRequest().getAttributes().get("city");
		logger.info("Request Url: " + URLCodecUtils.decoder(getReference().toString(), "utf-8") + ".");
	}

	@Get("json")
	public Object retriveUserInfo() {
		if (province == null || province.length() == 0 || !JavaPattern.isAllNum(province) || city == null
				|| city.length() == 0 || !JavaPattern.isAllNum(city)) {
			logger.error("Params `province` or `city` is null.");
			return new ErrorResponse.Builder(-1, "params error!").build();
		}
		List<SinaUserBaseInfo> baseinfos = application.getUserInfosByLocation("sina_user_baseinfo",
				Integer.parseInt(province), Integer.parseInt(city), 20);
		return baseinfos;
	}

}

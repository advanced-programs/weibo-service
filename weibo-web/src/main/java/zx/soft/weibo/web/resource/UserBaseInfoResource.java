package zx.soft.weibo.web.resource;

import java.util.HashMap;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.chars.JavaPattern;
import zx.soft.utils.codec.URLCodecUtils;
import zx.soft.weibo.web.application.UserInfoApplication;
import zx.soft.weibo.web.common.RestletRequestParams;
import zx.soft.weibo.web.domain.ErrorResponse;
import zx.soft.weibo.web.domain.SinaUserInfoResult;
import zx.soft.weibo.web.domain.TencentUserInfoResult;

public class UserBaseInfoResource extends ServerResource {

	private static Logger logger = LoggerFactory.getLogger(UserBaseInfoResource.class);

	private UserInfoApplication application;

	private String type = "";
	private String province = "";
	private String city = "";
	private String start = "";
	private String rows = "";

	@Override
	public void doInit() {
		application = (UserInfoApplication) getApplication();
		type = (String) this.getRequest().getAttributes().get("type");
		province = (String) this.getRequest().getAttributes().get("province");
		city = (String) this.getRequest().getAttributes().get("city");
		logger.info("Request Url: " + URLCodecUtils.decoder(getReference().toString(), "utf-8") + ".");
		HashMap<String, String> params = RestletRequestParams.getRequestParams(getRequest());
		if (params.size() != 2) {
			logger.error("Params `type` or `province` or `city` or `start` or `rows` is error.");
		}
		start = params.get("start");
		rows = params.get("rows");
	}

	@Get("json")
	public Object retriveUserInfo() {
		if (type == null || type.length() == 0 || province == null || province.length() == 0
				|| !JavaPattern.isAllNum(province) || city == null || city.length() == 0 || !JavaPattern.isAllNum(city)
				|| start == null || start.length() == 0 || !JavaPattern.isAllNum(start) || rows == null
				|| rows.length() == 0 || !JavaPattern.isAllNum(rows)) {
			logger.error("Params `type` or `province` or `city` or `start` or `rows` is null.");
			return new ErrorResponse.Builder(-1, "Params `type` or `province` or `city` or `start` or `rows` is null!")
					.build();
		}
		if ("sina".equalsIgnoreCase(type)) {
			return new SinaUserInfoResult(
					application.getSinaUsersCountByLocation("sina_user_baseinfo", province, city),
					application.getSinaUserInfosByLocation("sina_user_baseinfo", province, city,
							Integer.parseInt(start), Integer.parseInt(rows)));
		} else if ("tencent".equalsIgnoreCase(type)) {
			return new TencentUserInfoResult(application.getTencentUsersCountByLocation("tencent_user_baseinfo",
					province, city), application.getTencentUserInfosByLocation("tencent_user_baseinfo", province, city,
					Integer.parseInt(start), Integer.parseInt(rows)));
		} else {
			return new ErrorResponse.Builder(-1, "param `type` is error!").build();
		}
	}

}

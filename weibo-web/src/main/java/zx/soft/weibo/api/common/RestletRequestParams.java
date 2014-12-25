package zx.soft.weibo.api.common;

import java.util.HashMap;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.Parameter;

/**
 * Restlet框架中获取请求参数列表
 * 
 * @author wanggang
 *
 */
public class RestletRequestParams {

	public static HashMap<String, String> getRequestParams(Request request) {
		HashMap<String, String> params = new HashMap<>();
		Form form = request.getResourceRef().getQueryAsForm();
		for (Parameter p : form) {
			// 重复参数以最后一个为准
			params.put(p.getName(), p.getValue());
		}
		return params;
	}

}

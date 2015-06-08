package cc.pp.sina.mapred.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * GET 查询
 * POST 添加
 * PUT 更新
 * DELETE 删除
 */
public class BozhukuHttpUtils {

	private static final String BOZHU_URL = "http://114.112.81.3:8083/bozhus";

	public static void doBozhuInfoConnectionKeepAlive(String url, String data, String method) throws HttpException,
			IOException {

		EntityEnclosingMethod httpMethod = null;
		if ("post".equals(method)) {
			httpMethod = new PostMethod(url);
		} else if ("put".equals(method)) {
			httpMethod = new PutMethod(url);
		} else {
			return;
		}
		httpMethod.setContentChunked(true);
		RequestEntity requestEntity = new StringRequestEntity(data, null,
				"UTF-8");
		httpMethod.setRequestEntity(requestEntity);
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setMaxTotalConnections(200);
		params.setDefaultMaxConnectionsPerHost(150);
		params.setConnectionTimeout(30000);
		params.setSoTimeout(30000);
		HttpClientParams clientParams = new HttpClientParams();
		clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient(
				clientParams, connectionManager);
		httpMethod.getParams()
				.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		httpMethod.addRequestHeader("Connection", "close");
		client.getParams().setBooleanParameter("http.protocol.expect-continue", false);

		client.executeMethod(httpMethod);
		int responseCode = httpMethod.getStatusCode();
		if (responseCode != 200 && responseCode != 201) {
			System.err.println(responseCode);
		}
		httpMethod.releaseConnection();
	}

	/**
	 * 返回博主信息的url
	 */
	public static String getBozhuInfoUrl(String username, String ptype) {
		return BOZHU_URL + "/" + username + "/" + ptype + "/statistics";
	}

	public static String getBozhuInfoUrl() {
		return BOZHU_URL + "/";
	}

	/**
	 * 查看博主价格url
	 */
	public static String getBozhuPriceUrl(String username, String ptype) {
		return BOZHU_URL + "/" + username + "/" + ptype + "/price";
	}

	public static String getBozhuPriceUrl(HashMap<String, String> params) {

		String url = BOZHU_URL + "/1/price?";
		for (Entry<String, String> param : params.entrySet()) {
			url = url + param.getKey() + "=" + param.getValue() + "&";
		}

		return url.substring(0, url.length() - 1);
	}

	public static String getBozhuPriceUrl() {
		return BOZHU_URL + "/1/price";
	}

}

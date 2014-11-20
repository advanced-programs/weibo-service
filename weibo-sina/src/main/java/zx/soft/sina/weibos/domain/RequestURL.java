package zx.soft.sina.weibos.domain;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 请求URL
 * 
 * @author wanggang
 *
 */
public class RequestURL {

	private final String baseURL;
	private final String superid;
	private final HashMap<String, String> params;

	public RequestURL(Builder builder) {
		this.baseURL = builder.baseURL;
		this.superid = builder.superid;
		this.params = builder.params;
	}

	public static class Builder {

		private String baseURL = "";
		private String superid = "";
		private HashMap<String, String> params = new HashMap<>();

		public Builder(String baseURL, String superid) {
			this.baseURL = baseURL;
			this.superid = superid;
			this.params = new HashMap<>();
		}

		public Builder setParams(HashMap<String, String> params) {
			this.params = params;
			return this;
		}

		public Builder setParams(String key, String value) {
			this.params.put(key, value);
			return this;
		}

		public RequestURL build() {
			return new RequestURL(this);
		}

	}

	public String getBaseURL() {
		return baseURL;
	}

	public String getSuperid() {
		return superid;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public String getURL() {
		String url = this.baseURL + "?source=" + this.superid;
		for (Entry<String, String> temp : params.entrySet()) {
			url += "&" + temp.getKey() + "=" + temp.getValue();
		}
		return url;
	}

	@Override
	public String toString() {
		String result = "RequestURL:{baseURL=" + this.baseURL + ",superid=" + this.superid;
		for (Entry<String, String> temp : params.entrySet()) {
			result += "," + temp.getKey() + "=" + temp.getValue();
		}
		result += "}";
		return result;
	}

}

package zx.soft.weibo.api.domain;

import java.util.List;

public class SinaUserInfoResult {

	private int numFound;
	private List<SinaUserBaseInfo> data;

	public SinaUserInfoResult(int numFound, List<SinaUserBaseInfo> data) {
		this.numFound = numFound;
		this.data = data;
	}

	public int getNumFound() {
		return numFound;
	}

	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}

	public List<SinaUserBaseInfo> getData() {
		return data;
	}

	public void setData(List<SinaUserBaseInfo> data) {
		this.data = data;
	}

}

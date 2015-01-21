package zx.soft.weibo.web.domain;

import java.util.List;

import zx.soft.weibo.dao.domain.TencentUserBaseInfo;

public class TencentUserInfoResult {

	private int numFound;
	private List<TencentUserBaseInfo> data;

	public TencentUserInfoResult(int numFound, List<TencentUserBaseInfo> data) {
		this.numFound = numFound;
		this.data = data;
	}

	public int getNumFound() {
		return numFound;
	}

	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}

	public List<TencentUserBaseInfo> getData() {
		return data;
	}

	public void setData(List<TencentUserBaseInfo> data) {
		this.data = data;
	}

}

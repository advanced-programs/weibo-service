package zx.soft.weibo.dao.domain.sina;

public class WeiboSelectParams {

	private String tablename;
	private long wid;

	public WeiboSelectParams() {
		//
	}

	public WeiboSelectParams(String tablename, long wid) {
		this.tablename = tablename;
		this.wid = wid;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public long getWid() {
		return wid;
	}

	public void setWid(long wid) {
		this.wid = wid;
	}

}

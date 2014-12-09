package zx.soft.weibo.dao.domain;

/**
 * 按时间段统计用户微博量输入参数
 * 
 * @author wanggang
 *
 */
public class UserWeibosGroupParams {

	private String tablename;
	private String uid;
	private String interval;
	private int limitn;

	public UserWeibosGroupParams() {
		//		
	}

	public UserWeibosGroupParams(String tablename, String uid, String interval, int limitn) {
		super();
		this.tablename = tablename;
		this.uid = uid;
		this.interval = interval;
		this.limitn = limitn;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public int getLimitn() {
		return limitn;
	}

	public void setLimitn(int limitn) {
		this.limitn = limitn;
	}

}

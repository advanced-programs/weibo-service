package zx.soft.weibo.dao.domain;

public class UserInfosByLocationParams {

	private String tablename;
	// 为了兼顾腾讯（string型）、新浪（int型），province和city为string型
	private String province;
	private String city;
	private int start;
	private int rows;

	public UserInfosByLocationParams(String tablename, String province, String city, int start, int rows) {
		this.tablename = tablename;
		this.province = province;
		this.city = city;
		this.start = start;
		this.rows = rows;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		// 因为mysql中limit start,rows，是从start+1开始计算的
		this.start = start - 1;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}

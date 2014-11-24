package zx.soft.weibo.api.domain;

public class UserInfosByLocationParams {

	private String tablename;
	private int province;
	private int city;
	private int start;
	private int rows;

	public UserInfosByLocationParams(String tablename, int province, int city, int start, int rows) {
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

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
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

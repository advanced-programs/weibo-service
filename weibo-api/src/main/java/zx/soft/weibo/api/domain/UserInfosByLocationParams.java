package zx.soft.weibo.api.domain;

public class UserInfosByLocationParams {

	private String tablename;
	private int province;
	private int city;
	private int count;

	public UserInfosByLocationParams(String tablename, int province, int city, int count) {
		this.tablename = tablename;
		this.province = province;
		this.city = city;
		this.count = count;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

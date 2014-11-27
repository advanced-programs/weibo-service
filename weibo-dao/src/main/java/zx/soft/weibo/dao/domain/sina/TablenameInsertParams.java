package zx.soft.weibo.dao.domain.sina;

public class TablenameInsertParams {

	private String tablename;
	private String name;

	public TablenameInsertParams() {
		//
	}

	public TablenameInsertParams(String tablename, String name) {
		this.tablename = tablename;
		this.name = name;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

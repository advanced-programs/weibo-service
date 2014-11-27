package zx.soft.weibo.dao.domain.sina;

public class WeibosSelectParams {

	private String tablename;
	private int low;
	private int high;

	public WeibosSelectParams() {
		//
	}

	public WeibosSelectParams(String tablename, int low, int high) {
		this.tablename = tablename;
		this.low = low;
		this.high = high;
	}

	public String getTablename() {
		return tablename;
	}

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}

}

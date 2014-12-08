package zx.soft.weibo.dao.domain.sina;

/**
 * 简单微博信息
 * 
 * @author wanggang
 *
 */
public class SimpleWeibosInfo {

	private final String wid;
	private final String uid;
	private final String nickname;
	private final String statusesCount;
	private final String year;
	private final String yearMonth;
	private final String yearMonthDay;
	private String weibo;

	public SimpleWeibosInfo(Builder builder) {
		this.wid = builder.wid;
		this.uid = builder.uid;
		this.nickname = builder.nickname;
		this.statusesCount = builder.statusesCount;
		this.year = builder.year;
		this.yearMonth = builder.yearMonth;
		this.yearMonthDay = builder.yearMonthDay;
		this.weibo = builder.weibo;
	}

	public static class Builder {

		private final String wid;
		private final String uid;
		private final String nickname;
		private String statusesCount;
		private String year;
		private String yearMonth;
		private String yearMonthDay;
		private String weibo;

		public Builder(String wid, String uid, String nickname) {
			this.wid = wid;
			this.uid = uid;
			this.nickname = nickname;
		}

		public Builder setStatusesCount(String statusesCount) {
			this.statusesCount = statusesCount;
			return this;
		}

		public Builder setYear(String year) {
			this.year = year;
			return this;
		}

		public Builder setYearMonth(String yearMonth) {
			this.yearMonth = yearMonth;
			return this;
		}

		public Builder setYearMonthDay(String yearMonthDay) {
			this.yearMonthDay = yearMonthDay;
			return this;
		}

		public Builder setWeibo(String weibo) {
			this.weibo = weibo;
			return this;
		}

		public SimpleWeibosInfo build() {
			return new SimpleWeibosInfo(this);
		}

	}

	public String getWid() {
		return wid;
	}

	public String getUid() {
		return uid;
	}

	public String getNickname() {
		return nickname;
	}

	public String getStatusesCount() {
		return statusesCount;
	}

	public String getYear() {
		return year;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public String getYearMonthDay() {
		return yearMonthDay;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

}

package zx.soft.weibo.dao.domain.sina;

import java.util.Date;

public class WeiboInsertParams {

	private final String tablename;
	private final long wid;
	private final long username;
	private final int repostscount;
	private final int commentscount;
	private final int attitudescount;
	private final String text;
	private final Date createat;
	private final long owid;
	private final long ousername;
	private final boolean favorited;
	private final String geo;
	private final double latitude;
	private final double longitude;
	private final String originalpic;
	private final String source;
	private final String visible;
	private final int mlevel;

	public WeiboInsertParams(Builder builder) {
		this.tablename = builder.tablename;
		this.wid = builder.wid;
		this.username = builder.username;
		this.repostscount = builder.repostscount;
		this.commentscount = builder.commentscount;
		this.attitudescount = builder.attitudescount;
		this.text = builder.text;
		this.createat = builder.createat;
		this.owid = builder.owid;
		this.ousername = builder.ousername;
		this.favorited = builder.favorited;
		this.geo = builder.geo;
		this.latitude = builder.latitude;
		this.longitude = builder.longitude;
		this.originalpic = builder.originalpic;
		this.source = builder.source;
		this.visible = builder.visible;
		this.mlevel = builder.mlevel;
	}

	public static class Builder {

		private final String tablename;
		private final long wid;
		private final long username;
		private int repostscount;
		private int commentscount;
		private int attitudescount;
		private String text = "";
		private Date createat;
		private long owid;
		private long ousername;
		private boolean favorited;
		private String geo = "";
		private double latitude;
		private double longitude;
		private String originalpic = "";
		private String source = "";
		private String visible = "";
		private int mlevel;

		public Builder(String tablename, long wid, long username) {
			this.tablename = tablename;
			this.wid = wid;
			this.username = username;
		}

		public Builder setRepostscount(int repostscount) {
			this.repostscount = repostscount;
			return this;
		}

		public Builder setCommentscount(int commentscount) {
			this.commentscount = commentscount;
			return this;
		}

		public Builder setAttitudescount(int attitudescount) {
			this.attitudescount = attitudescount;
			return this;
		}

		public Builder setText(String text) {
			this.text = text;
			return this;
		}

		public Builder setCreateat(Date createat) {
			this.createat = createat;
			return this;
		}

		public Builder setOwid(long owid) {
			this.owid = owid;
			return this;
		}

		public Builder setOusername(long ousername) {
			this.ousername = ousername;
			return this;
		}

		public Builder setFavorited(boolean favorited) {
			this.favorited = favorited;
			return this;
		}

		public Builder setGeo(String geo) {
			this.geo = geo;
			return this;
		}

		public Builder setLatitude(double latitude) {
			this.latitude = latitude;
			return this;
		}

		public Builder setLongitude(double longitude) {
			this.longitude = longitude;
			return this;
		}

		public Builder setOriginalpic(String originalpic) {
			this.originalpic = originalpic;
			return this;
		}

		public Builder setSource(String source) {
			this.source = source;
			return this;
		}

		public Builder setVisible(String visible) {
			this.visible = visible;
			return this;
		}

		public Builder setMlevel(int mlevel) {
			this.mlevel = mlevel;
			return this;
		}

		public WeiboInsertParams build() {
			return new WeiboInsertParams(this);
		}

	}

	public String getTablename() {
		return tablename;
	}

	public long getWid() {
		return wid;
	}

	public long getUsername() {
		return username;
	}

	public int getRepostscount() {
		return repostscount;
	}

	public int getCommentscount() {
		return commentscount;
	}

	public int getAttitudescount() {
		return attitudescount;
	}

	public String getText() {
		return text;
	}

	public Date getCreateat() {
		return createat;
	}

	public long getOwid() {
		return owid;
	}

	public long getOusername() {
		return ousername;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public String getGeo() {
		return geo;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getOriginalpic() {
		return originalpic;
	}

	public String getSource() {
		return source;
	}

	public String getVisible() {
		return visible;
	}

	public int getMlevel() {
		return mlevel;
	}

}

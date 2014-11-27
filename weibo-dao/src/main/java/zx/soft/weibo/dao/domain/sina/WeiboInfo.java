package zx.soft.weibo.dao.domain.sina;

public class WeiboInfo {

	private long wid;
	private long username;
	private int repostscount;
	private int commentscount;
	private int attitudescount;
	private String text;
	private long createat;
	private long owid;
	private long ousername;
	private boolean favorited;
	private String geo;
	private double latitude;
	private double longitude;
	private String originalpic;
	private String source;
	private String visible;
	private int mlevel;
	private long lasttime;

	public long getWid() {
		return wid;
	}

	public void setWid(long wid) {
		this.wid = wid;
	}

	public long getUsername() {
		return username;
	}

	public void setUsername(long username) {
		this.username = username;
	}

	public int getRepostscount() {
		return repostscount;
	}

	public void setRepostscount(int repostscount) {
		this.repostscount = repostscount;
	}

	public int getCommentscount() {
		return commentscount;
	}

	public void setCommentscount(int commentscount) {
		this.commentscount = commentscount;
	}

	public int getAttitudescount() {
		return attitudescount;
	}

	public void setAttitudescount(int attitudescount) {
		this.attitudescount = attitudescount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getCreateat() {
		return createat;
	}

	public void setCreateat(long createat) {
		this.createat = createat;
	}

	public long getOwid() {
		return owid;
	}

	public void setOwid(long owid) {
		this.owid = owid;
	}

	public long getOusername() {
		return ousername;
	}

	public void setOusername(long ousername) {
		this.ousername = ousername;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getOriginalpic() {
		return originalpic;
	}

	public void setOriginalpic(String originalpic) {
		this.originalpic = originalpic;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public int getMlevel() {
		return mlevel;
	}

	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}

	public long getLasttime() {
		return lasttime;
	}

	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}

}

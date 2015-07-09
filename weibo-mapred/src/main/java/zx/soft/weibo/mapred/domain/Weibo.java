package zx.soft.weibo.mapred.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 新浪单条微博信息
 *
 * @author wanggang
 *
 */
public class Weibo implements Serializable {

	private static final long serialVersionUID = -6844154705443750491L;

	// 微博创建时间
	private Date created_at;
	// 微博ID
	private String id;
	private String mid;
	private String idstr;
	// 微博内容
	private String text;
	// 微博来源是否允许点击
	private int source_allowclick;
	// 微博来源类型
	private int source_type;
	// 微博来源信息
	private String source;
	// 是否收藏
	private boolean favorited;
	// 是否被删除
	private boolean truncated;
	// 回复微博id
	private String in_reply_to_status_id;
	// 回复用户id
	private String in_reply_to_user_id;
	// 回复用户昵称
	private String in_reply_to_screen_name;
	// 图片地址
	private List<String> pic_urls;
	// 地址位置信息
	private String geo;
	// 发博用户信息
	private User user;
	// 转发数
	private int reposts_count;
	// 评论数
	private int comments_count;
	// 赞数
	private int attitudes_count;
	// 微博等级
	private int mlevel;
	// 可见信息
	private Visible visible;
	// 未知标签信息
	private List<String> darwin_tags;
	// 转发微博ID
	private long owid;
	// 转发微博用户名
	private long ousername;

	public Weibo() {
		super();
	}

	public Weibo(Builder builder) {

		this.created_at = builder.created_at;
		this.id = builder.id;
		this.mid = builder.mid;
		this.idstr = builder.idstr;
		this.text = builder.text;
		this.source_allowclick = builder.source_allowclick;
		this.source_type = builder.source_type;
		this.source = builder.source;
		this.favorited = builder.favorited;
		this.truncated = builder.truncated;
		this.in_reply_to_status_id = builder.in_reply_to_status_id;
		this.in_reply_to_user_id = builder.in_reply_to_user_id;
		this.in_reply_to_screen_name = builder.in_reply_to_screen_name;
		this.pic_urls = builder.pic_urls;
		this.geo = builder.geo;
		this.user = builder.user;
		this.reposts_count = builder.reposts_count;
		this.comments_count = builder.comments_count;
		this.attitudes_count = builder.attitudes_count;
		this.mlevel = builder.mlevel;
		this.visible = builder.visible;
		this.darwin_tags = builder.darwin_tags;
		this.owid = builder.owid;
		this.ousername = builder.ousername;
	}

	public static class Builder {

		private Date created_at;
		private String id;
		private String mid;
		private String idstr;
		private String text = "";
		private int source_allowclick;
		private int source_type;
		private String source = "";
		private boolean favorited;
		private boolean truncated;
		private String in_reply_to_status_id = "";
		private String in_reply_to_user_id = "";
		private String in_reply_to_screen_name = "";
		private List<String> pic_urls = null;
		private String geo = "";
		private User user = null;
		private int reposts_count;
		private int comments_count;
		private int attitudes_count;
		private int mlevel;
		private Visible visible = null;
		private List<String> darwin_tags = null;
		private long owid;
		private long ousername;

		public Builder(String id, String mid, String idstr, Date created_at) {
			super();
			this.id = id;
			this.mid = mid;
			this.idstr = idstr;
			this.created_at = created_at;
		}

		public Builder setText(String text) {
			this.text = text;
			return this;
		}

		public Builder setSource_allowclick(int source_allowclick) {
			this.source_allowclick = source_allowclick;
			return this;
		}

		public Builder setSource_type(int source_type) {
			this.source_type = source_type;
			return this;
		}

		public Builder setSource(String source) {
			this.source = source;
			return this;
		}

		public Builder setFavorited(boolean favorited) {
			this.favorited = favorited;
			return this;
		}

		public Builder setTruncated(boolean truncated) {
			this.truncated = truncated;
			return this;
		}

		public Builder setIn_reply_to_status_id(String in_reply_to_status_id) {
			this.in_reply_to_status_id = in_reply_to_status_id;
			return this;
		}

		public Builder setIn_reply_to_user_id(String in_reply_to_user_id) {
			this.in_reply_to_user_id = in_reply_to_user_id;
			return this;
		}

		public Builder setIn_reply_to_screen_name(String in_reply_to_screen_name) {
			this.in_reply_to_screen_name = in_reply_to_screen_name;
			return this;
		}

		public Builder setPic_urls(List<String> pic_urls) {
			this.pic_urls = pic_urls;
			return this;
		}

		public Builder setGeo(String geo) {
			this.geo = geo;
			return this;
		}

		public Builder setUser(User user) {
			this.user = user;
			return this;
		}

		public Builder setReposts_count(int reposts_count) {
			this.reposts_count = reposts_count;
			return this;
		}

		public Builder setComments_count(int comments_count) {
			this.comments_count = comments_count;
			return this;
		}

		public Builder setAttitudes_count(int attitudes_count) {
			this.attitudes_count = attitudes_count;
			return this;
		}

		public Builder setMlevel(int mlevel) {
			this.mlevel = mlevel;
			return this;
		}

		public Builder setVisible(Visible visible) {
			this.visible = visible;
			return this;
		}

		public Builder setDarwin_tags(List<String> darwin_tags) {
			this.darwin_tags = darwin_tags;
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

		public Weibo build() {
			return new Weibo(this);
		}

	}

	@Override
	public String toString() {
		return "Weibo [created_at=" + created_at + ", id=" + id + ", mid=" + mid + ", idstr=" + idstr + ", text="
				+ text + ", source_allowclick=" + source_allowclick + ", source_type=" + source_type + ", source="
				+ source + ", favorited=" + favorited + ", truncated=" + truncated + ", in_reply_to_status_id="
				+ in_reply_to_status_id + ", in_reply_to_user_id=" + in_reply_to_user_id + ", in_reply_to_screen_name="
				+ in_reply_to_screen_name + ", pic_urls=" + pic_urls + ", geo=" + geo + ", user=" + user
				+ ", reposts_count=" + reposts_count + ", comments_count=" + comments_count + ", attitudes_count="
				+ attitudes_count + ", mlevel=" + mlevel + ", visible=" + visible + ", darwin_tags=" + darwin_tags
				+ ", owid=" + owid + ", ousername=" + ousername + "]";
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getSource_allowclick() {
		return source_allowclick;
	}

	public void setSource_allowclick(int source_allowclick) {
		this.source_allowclick = source_allowclick;
	}

	public int getSource_type() {
		return source_type;
	}

	public void setSource_type(int source_type) {
		this.source_type = source_type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public boolean isTruncated() {
		return truncated;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}

	public void setIn_reply_to_status_id(String in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}

	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}

	public void setIn_reply_to_user_id(String in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}

	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}

	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}

	public List<String> getPic_urls() {
		return pic_urls;
	}

	public void setPic_urls(List<String> pic_urls) {
		this.pic_urls = pic_urls;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getReposts_count() {
		return reposts_count;
	}

	public void setReposts_count(int reposts_count) {
		this.reposts_count = reposts_count;
	}

	public int getComments_count() {
		return comments_count;
	}

	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}

	public int getAttitudes_count() {
		return attitudes_count;
	}

	public void setAttitudes_count(int attitudes_count) {
		this.attitudes_count = attitudes_count;
	}

	public int getMlevel() {
		return mlevel;
	}

	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}

	public Visible getVisible() {
		return visible;
	}

	public void setVisible(Visible visible) {
		this.visible = visible;
	}

	public List<String> getDarwin_tags() {
		return darwin_tags;
	}

	public void setDarwin_tags(List<String> darwin_tags) {
		this.darwin_tags = darwin_tags;
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

}

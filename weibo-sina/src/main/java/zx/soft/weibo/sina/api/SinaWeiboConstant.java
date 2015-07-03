package zx.soft.weibo.sina.api;

/**
 * 新浪微博API相关常量
 *
 * @author wanggang
 *
 */
public class SinaWeiboConstant {

	/**
	 * 基本接口
	 * source:采用OAuth授权方式不需要此参数，其他授权方式为必填参数，数值为应用的AppKey。
	 * https://api.weibo.com/2/
	 */
	public static final String ROOT_URL = "http://api.weibo.com/2/";

	/**
	 * 1、粉丝服务
	 */

	/**
	 * 2、微博
	 */
	/*
	 * 微博目录
	 */
	private static final String STATUSES = "statuses/";

	/*
	 * 返回最新的200条公共微博，返回结果非完全实时
	 * count:单页返回的记录条数，最大不超过200，默认为20
	 */
	public static final String STATUSES_PUBLIC_TIMELINE = ROOT_URL + STATUSES + "public_timeline.json";

	/*
	 * 获取某个用户最新发表的微博列表
	 * uid:需要查询的用户ID。
	 * screen_name:需要查询的用户昵称。
	 * since_id:若指定此参数，则返回ID比since_id大的微博（即比since_id时间晚的微博），默认为0。
	 * max_id:若指定此参数，则返回ID小于或等于max_id的微博，默认为0。
	 * count:单页返回的记录条数，最大不超过100，超过100以100处理，默认为20。
	 * page:返回结果的页码，默认为1。
	 * base_app:是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0。
	 * feature	:过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
	 * trim_user:返回值中user字段开关，0：返回完整user字段、1：user字段仅返回user_id，默认为0。
	 */
	public static final String STATUSES_USER_TIMELINE = ROOT_URL + STATUSES + "user_timeline.json";

	/**
	 * 3、评论
	 */
	/*
	 * 评论目录
	 */
	private static final String COMMENTS = "comments/";

	/*
	 * 根据微博ID返回某条微博的评论列表
	 * id:需要查询的微博ID。
	 * since_id:若指定此参数，则返回ID比since_id大的评论（即比since_id时间晚的评论），默认为0。
	 * max_id:若指定此参数，则返回ID小于或等于max_id的评论，默认为0。
	 * count:单页返回的记录条数，默认为50。
	 * page:返回结果的页码，默认为1。
	 * filter_by_author:作者筛选类型，0：全部、1：我关注的人、2：陌生人，默认为0。
	 */
	public static final String COMMENTS_SHOW = ROOT_URL + COMMENTS + "show.json";

	/**
	 * 4、用户
	 */
	/*
	 * 用户目录
	 */
	private static final String USERS = "users/";

	/*
	 * 根据用户ID获取用户信息
	 * uid:需要查询的用户ID
	 */
	public static final String USERS_SHOW = ROOT_URL + USERS + "show.json";

	/**
	 * 5、置顶微博
	 */

	/**
	 * 6、关系
	 */
	/*
	 * 关系目录
	 */
	private static final String FRIENDSHIPS = "friendships/";

	/*
	 * 获取用户的关注列表
	 * 	uid	: 需要查询的用户UID。
	 * screen_name: 需要查询的用户昵称。
	 * count: 单页返回的记录条数，默认为50，最大不超过200。
	 * cursor: 返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0。
	 * trim_status: 返回值中user字段中的status字段开关，0：返回完整status字段、1：status字段仅返回status_id，默认为1。
	 */
	public static final String FRIEDNSHIPS_FRIEDNS = ROOT_URL + FRIENDSHIPS + "friends.json";

	/*
	 * 获取用户的粉丝列表
	 * uid	: 需要查询的用户UID。
	 * screen_name: 需要查询的用户昵称。
	 * count: 单页返回的记录条数，默认为50，最大不超过200。
	 * cursor: 返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0。
	 * trim_status: 返回值中user字段中的status字段开关，0：返回完整status字段、1：status字段仅返回status_id，默认为1。
	 */
	public static final String FRIENDSHIPS_FOLLOWERS = ROOT_URL + FRIENDSHIPS + "followers.json";

	/*
	 * 关注子目录
	 */
	private static final String FRIENDS = "friends/";

	/*
	 * 获取用户关注的用户UID列表:
	 * uid	: 需要查询的用户UID。
	 * screen_name: 需要查询的用户昵称。
	 * count: 单页返回的记录条数，默认为500，最大不超过5000。
	 * cursor: 返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0。
	 */
	public static final String FRIENDSHIPS_FRIENDS_IDS = ROOT_URL + FRIENDSHIPS + FRIENDS + "ids.json";

	/*
	 * 粉丝子目录
	 */
	private static final String FOLLOWERS = "followers/";

	/*
	 * 获取用户粉丝的用户UID列表
	 * uid: 需要查询的用户UID。
	 * screen_name: 需要查询的用户昵称。
	 * count: 单页返回的记录条数，默认为500，最大不超过5000。
	 * cursor: 返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0。
	 */
	public static final String FRIEDNSHIPS_FOLLOWERS_IDS = ROOT_URL + FRIENDSHIPS + FOLLOWERS + "ids.json";

	/*
	 * 获取用户的活跃粉丝列表
	 * uid: 需要查询的用户UID。
	 * count: 返回的记录条数，默认为20，最大不超过200。
	 */
	public static final String FRIENDSHIPS_FOLLOWERS_ACTIVE = ROOT_URL + FRIENDSHIPS + FOLLOWERS + "active.json";

	/**
	 * 7、好友分组
	 */

	/**
	 * 8、账号
	 */

	/**
	 * 9、收藏
	 */

	/**
	 * 10、话题
	 */

	/**
	 * 11、微博标签
	 */

	/**
	 * 12、用户标签
	 */

	/**
	 * 13、注册
	 */

	/**
	 * 14、搜索
	 */

	/**
	 * 15、推荐
	 */

	/**
	 * 16、提醒
	 */

	/**
	 * 17、短链
	 */

	/**
	 * 18、公共服务
	 */

	/**
	 * 19、位置服务
	 */

	/**
	 * 20、地理信息
	 */

	/**
	 * 21、OAuth2
	 */

}

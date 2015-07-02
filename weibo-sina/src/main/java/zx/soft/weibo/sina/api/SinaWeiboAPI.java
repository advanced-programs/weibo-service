package zx.soft.weibo.sina.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.chars.JavaPattern;
import zx.soft.utils.config.ConfigUtil;
import zx.soft.utils.http.ClientDao;
import zx.soft.utils.json.JsonNodeUtils;
import zx.soft.weibo.sina.domain.RequestURL;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 新浪微博API
 *
 * @author wanggang
 *
 */
public class SinaWeiboAPI {

	private static Logger logger = LoggerFactory.getLogger(SinaWeiboAPI.class);

	private static String superid;

	static {
		logger.info("Adding superID ...");
		Properties props = ConfigUtil.getProps("super.properties");
		superid = props.getProperty("super");
		logger.info("Finishing adding superID ...");
	}

	private final ClientDao clientDao;

	public SinaWeiboAPI(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	/**
	 * 2、微博
	 */

	/*
	 * 返回最新的200条公共微博，返回结果非完全实时
	 * count:单页返回的记录条数，最大不超过200，默认为20
	 */
	public SinaDomain statusesPublicTimeline(int count) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.STATUSES_PUBLIC_TIMELINE, superid).setParams(
				"count", Math.min(count, 200) + "").build();
		String data = clientDao.doGet(requestURL.getURL());
		SinaDomain result = parseJsonTree(data);
		return result;
	}

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
	public SinaDomain statusesUserTimelineByUid(String uid, String sinceId, String maxId, int count, int page,
			int baseApp, int feature, int trimUser) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.STATUSES_USER_TIMELINE, superid)
				.setParams("uid", uid).setParams("since_id", sinceId).setParams("max_id", maxId)
				.setParams("count", count + "").setParams("page", page + "").setParams("base_app", baseApp + "")
				.setParams("feature", feature + "").setParams("trim_user", trimUser + "").build();
		String data = clientDao.doGet(requestURL.getURL());
		SinaDomain result = parseJsonTree(data);
		return result;
	}

	public SinaDomain statusesUserTimelineByScreenName(String screenName, String sinceId, String maxId, int count,
			int page, int baseApp, int feature, int trimUser) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.STATUSES_USER_TIMELINE, superid)
				.setParams("screen_name", screenName).setParams("since_id", sinceId).setParams("max_id", maxId)
				.setParams("count", count + "").setParams("page", page + "").setParams("base_app", baseApp + "")
				.setParams("feature", feature + "").setParams("trim_user", trimUser + "").build();
		String data = clientDao.doGet(requestURL.getURL());
		SinaDomain result = parseJsonTree(data);
		return result;
	}

	/**
	 * 3、评论
	 */

	/*
	 * 根据微博ID返回某条微博的评论列表，可能需要传入cookie参数。
	 * id:需要查询的微博ID。
	 * since_id:若指定此参数，则返回ID比since_id大的评论（即比since_id时间晚的评论），默认为0。
	 * max_id:若指定此参数，则返回ID小于或等于max_id的评论，默认为0。
	 * count:单页返回的记录条数，默认为50。
	 * page:返回结果的页码，默认为1。
	 * filter_by_author:作者筛选类型，0：全部、1：我关注的人、2：陌生人，默认为0。
	 */
	public SinaDomain commentsShow(String cookie, String wid, String sinceId, String maxId, int count, int page,
			int filterByAuthor) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.COMMENTS_SHOW, superid).setParams("id", wid)
				.setParams("since_id", sinceId).setParams("max_id", maxId).setParams("count", count + "")
				.setParams("page", page + "").setParams("filter_by_author", filterByAuthor + "").build();
		String data = clientDao.doGet(requestURL.getURL(), cookie, "UTF-8");
		SinaDomain result = parseJsonTree(data);
		return result;
	}

	/**
	 * 4、用户
	 */

	/*
	 * 根据用户ID获取用户信息
	 * uid:需要查询的用户ID
	 */
	public SinaDomain usersShow(String uid) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.USERS_SHOW, superid).setParams("uid", uid)
				.build();
		String data = clientDao.doGet(requestURL.getURL());
		SinaDomain result = parseJsonTree(data);
		return result;
	}

	/**
	 * 6、关系
	 */

	/*
	 * 获取用户的关注列表
	 * 	uid	: 需要查询的用户UID。
	 * screen_name: 需要查询的用户昵称。
	 * count: 单页返回的记录条数，默认为50，最大不超过200。
	 * cursor: 返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0。
	 * trim_status: 返回值中user字段中的status字段开关，0：返回完整status字段、1：status字段仅返回status_id，默认为1。
	 */
	public SinaDomain friendshipsFriends(String uid, int count, int cursor, int trim_status) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.FRIEDNSHIPS_FRIEDNS, superid)
				.setParams("uid", uid).setParams("count", count + "").setParams("cursor", cursor + "")
				.setParams("trim_status", trim_status + "").build();
		String data = clientDao.doGet(requestURL.getURL());
		SinaDomain result = parseJsonTree(data);
		return result;
	}

	/*
	 * 获取用户关注的用户UID列表:
	 * uid	: 需要查询的用户UID。
	 * screen_name: 需要查询的用户昵称。
	 * count: 单页返回的记录条数，默认为500，最大不超过5000。
	 * cursor: 返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0。
	 */
	public SinaDomain friendshipsFriendsIDs(String uid, int count, int cursor, String cookie) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.FRIENDSHIPS_FRIENDS_IDS, superid)
				.setParams("uid", uid).setParams("count", count + "").setParams("cursor", cursor + "").build();
		String data = clientDao.doGet(requestURL.getURL(), cookie, "UTF-8");
		SinaDomain result = parseJsonTreeIDs(data);
		return result;
	}

	/*
	 * 获取用户的粉丝列表
	 * uid	: 需要查询的用户UID。
	 * screen_name: 需要查询的用户昵称。
	 * count: 单页返回的记录条数，默认为50，最大不超过200。
	 * cursor: 返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0。
	 * trim_status: 返回值中user字段中的status字段开关，0：返回完整status字段、1：status字段仅返回status_id，默认为1。
	 */
	public SinaDomain friendshipsFollowers(String uid, int count, int cursor, int trim_status) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.FRIENDSHIPS_FOLLOWERS, superid)
				.setParams("uid", uid).setParams("count", count + "").setParams("cursor", cursor + "")
				.setParams("trim_status", trim_status + "").build();
		String data = clientDao.doGet(requestURL.getURL());
		SinaDomain result = parseJsonTree(data);
		return result;
	}

	/*
	 * 获取用户粉丝的用户UID列表
	 * uid: 需要查询的用户UID。
	 * screen_name: 需要查询的用户昵称。
	 * count: 单页返回的记录条数，默认为500，最大不超过5000。
	 * cursor: 返回结果的游标，下一页用返回值里的next_cursor，上一页用previous_cursor，默认为0。
	 */
	public SinaDomain friendshipsFollowersIDs(String uid, int count, int cursor, String cookie) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.FRIEDNSHIPS_FOLLOWERS_IDS, superid)
				.setParams("uid", uid).setParams("count", count + "").setParams("cursor", cursor + "").build();
		String data = clientDao.doGet(requestURL.getURL(), cookie, "UTF-8");
		SinaDomain result = parseJsonTreeIDs(data);
		return result;
	}

	/*
	 * 获取用户的活跃粉丝列表
	 * uid: 需要查询的用户UID。
	 * count: 返回的记录条数，默认为20，最大不超过200。
	 */
	public SinaDomain friendshipsFollowersActive(String uid, int count, int cursor) {
		RequestURL requestURL = new RequestURL.Builder(SinaWeiboConstant.FRIENDSHIPS_FOLLOWERS_ACTIVE, superid)
				.setParams("uid", uid).setParams("count", count + "").setParams("cursor", cursor + "").build();
		String data = clientDao.doGet(requestURL.getURL());
		SinaDomain result = parseJsonTree(data);
		return result;
	}

	/**
	 * JSON树解析方法
	 * @param jsonStr：json字符串
	 * @return 解析好的JSON对象
	 */
	private SinaDomain parseJsonTree(String jsonStr) {
		SinaDomain result = new SinaDomain();
		JsonNode node = JsonNodeUtils.getJsonNode(jsonStr);
		Iterator<String> fieldNames = node.getFieldNames();
		String field = "", value = "";
		JsonNode tnode = null;
		while (fieldNames.hasNext()) {
			field = fieldNames.next();
			if (JsonNodeUtils.getJsonNode(node, field).size() != 0) {
				tnode = JsonNodeUtils.getJsonNode(node, field);
				if (tnode.isArray()) {
					List<Object> arr = new ArrayList<>();
					for (JsonNode nodet : tnode) {
						arr.add(parseJsonTree(nodet.toString()));
					}
					result.put(field, arr);
				} else {
					result.put(field, parseJsonTree(tnode.toString()));
				}
				continue;
			}
			value = node.get(field).toString().replaceAll("\"", "");
			if (value.length() > 0 && value.length() < 15 && JavaPattern.isAllNum(value) && !value.contains("-")) {
				if (value.contains(".") | value.contains("+")) {
					result.addField(field, Double.parseDouble(value));
				} else {
					result.addField(field, Long.parseLong(value));
				}
			} else {
				result.addField(field, value);
			}
		}

		return result;
	}

	/**
	 * 针对关注和粉丝ID列表
	 * @param jsonStr
	 * @return
	 */
	private SinaDomain parseJsonTreeIDs(String jsonStr) {
		SinaDomain result = new SinaDomain();
		JsonNode node = JsonNodeUtils.getJsonNode(jsonStr);
		Iterator<String> fieldNames = node.getFieldNames();
		String field = "", value = "";
		JsonNode tnode = null;
		while (fieldNames.hasNext()) {
			field = fieldNames.next();
			if (JsonNodeUtils.getJsonNode(node, field).size() != 0) {
				tnode = JsonNodeUtils.getJsonNode(node, field);
				if (tnode.isArray()) {
					List<Object> arr = new ArrayList<>();
					for (JsonNode nodet : tnode) {
						arr.add(nodet.toString());
					}
					result.put(field, arr);
				} else {
					result.put(field, parseJsonTree(tnode.toString()));
				}
				continue;
			}
			value = node.get(field).toString().replaceAll("\"", "");
			if (value.length() > 0 && value.length() < 15 && JavaPattern.isAllNum(value) && !value.contains("-")) {
				if (value.contains(".") | value.contains("+")) {
					result.addField(field, Double.parseDouble(value));
				} else {
					result.addField(field, Long.parseLong(value));
				}
			} else {
				result.addField(field, value);
			}
		}

		return result;
	}

}

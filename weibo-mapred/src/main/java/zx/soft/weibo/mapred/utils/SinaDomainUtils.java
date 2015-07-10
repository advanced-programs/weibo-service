package zx.soft.weibo.mapred.utils;

import java.util.ArrayList;
import java.util.List;

import zx.soft.utils.time.TimeUtils;
import zx.soft.weibo.mapred.domain.User;
import zx.soft.weibo.mapred.domain.UsersAndIds;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 新浪对象转换工具
 *
 * @author wanggang
 *
 */
public class SinaDomainUtils {

	public static UsersAndIds getUsersAndIds(SinaDomain sinaDomain) {
		List<User> users = new ArrayList<>();
		List<String> ids = new ArrayList<>();
		SinaDomain tmp = null;
		for (Object friend : sinaDomain.getFieldValues("users")) {
			tmp = (SinaDomain) friend;
			ids.add(getString(tmp, "idstr"));
			users.add(new User.Builder(getLong(tmp, "id"), getString(tmp, "idstr"), getString(tmp, "screen_name"),
					getString(tmp, "name"), TimeUtils.tranSinaApiDate(getString(tmp, "created_at")))
					.setUclass(getInt(tmp, "class")).setProvince(getInt(tmp, "province")).setCity(getInt(tmp, "city"))
					.setLocation(getString(tmp, "location")).setDescription(getString(tmp, "description"))
					.setUrl(getString(tmp, "url")).setProfile_image_url(getString(tmp, "profile_image_url"))
					.setProfile_url(getString(tmp, "profile_url")).setDomain(getString(tmp, "domain"))
					.setWeihao(getString(tmp, "weihao")).setGender(getString(tmp, "gender"))
					.setFollowers_count(getInt(tmp, "followers_count")).setFriends_count(getInt(tmp, "friends_count"))
					.setPagefriends_count(getInt(tmp, "pagefriends_count"))
					.setStatuses_count(getInt(tmp, "statuses_count"))
					.setFavourites_count(getInt(tmp, "favourites_count")).setFollowing(getBoolean(tmp, "following"))
					.setAllow_all_act_msg(getBoolean(tmp, "allow_all_act_msg"))
					.setGeo_enabled(getBoolean(tmp, "geo_enabled")).setVerified(getBoolean(tmp, "verified"))
					.setVerified_type(Integer.parseInt(tmp.getFieldValue("verified_type").toString()))
					.setRemark(getString(tmp, "remark")).setPtype(getInt(tmp, "ptype"))
					.setAllow_all_comment(getBoolean(tmp, "allow_all_comment"))
					.setAvatar_large(getString(tmp, "avatar_large")).setAvatar_hd(getString(tmp, "avatar_hd"))
					.setVerified_reason(getString(tmp, "verified_reason"))
					.setVerified_trade(getInt(tmp, "verified_trade"))
					.setVerified_reason_url(getString(tmp, "verified_reason_url"))
					.setVerified_source(getString(tmp, "verified_source"))
					.setVerified_source_url(getString(tmp, "verified_source_url"))
					.setVerified_state(getInt(tmp, "verified_state")).setVerified_level(getInt(tmp, "verified_level"))
					.setVerified_reason_modified(getString(tmp, "verified_reason_modified"))
					.setVerified_contact_name(getString(tmp, "verified_contact_name"))
					.setVerified_contact_email(getString(tmp, "verified_contact_email"))
					.setVerified_contact_mobile(getString(tmp, "verified_contact_mobile"))
					.setFollow_me(getBoolean(tmp, "follow_me")).setOnline_status(getInt(tmp, "online_status"))
					.setBi_followers_count(getInt(tmp, "bi_followers_count")).setLang(getString(tmp, "lang"))
					.setStar(getInt(tmp, "star")).setMbtype(getInt(tmp, "mbtype")).setMbrank(getInt(tmp, "mbrank"))
					.setBlock_word(getInt(tmp, "block_word")).setBlock_app(getInt(tmp, "block_app"))
					.setCredit_score(getInt(tmp, "credit_score")).setUser_ability(getInt(tmp, "user_ability"))
					.setUrank(getInt(tmp, "urank")).build());
		}
		return new UsersAndIds(users, ids);
	}

	private static String getString(SinaDomain sinaDomain, String key) {
		try {
			return sinaDomain.getFieldValue(key).toString();
		} catch (Exception e) {
			return "";
		}
	}

	private static int getInt(SinaDomain sinaDomain, String key) {
		try {
			return Integer.parseInt(sinaDomain.getFieldValue(key).toString());
		} catch (Exception e) {
			return 0;
		}
	}

	private static long getLong(SinaDomain sinaDomain, String key) {
		try {
			return (long) sinaDomain.getFieldValue(key);
		} catch (Exception e) {
			return 0L;
		}
	}

	private static boolean getBoolean(SinaDomain sinaDomain, String key) {
		try {
			return sinaDomain.getFieldValue(key).toString().equalsIgnoreCase("true") ? Boolean.TRUE : Boolean.FALSE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

}

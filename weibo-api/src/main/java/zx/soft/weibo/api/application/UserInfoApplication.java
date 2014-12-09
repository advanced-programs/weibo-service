package zx.soft.weibo.api.application;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import zx.soft.weibo.api.domain.UserWeibosGroup;
import zx.soft.weibo.api.resource.UserBaseInfoResource;
import zx.soft.weibo.api.resource.UserWeibosInfoResource;
import zx.soft.weibo.dao.common.MybatisConfig;
import zx.soft.weibo.dao.domain.SinaUserBaseInfo;
import zx.soft.weibo.dao.domain.TencentUserBaseInfo;
import zx.soft.weibo.dao.domain.sina.WeiboDayCount;
import zx.soft.weibo.dao.weibos.WeibosDaoImpl;

public class UserInfoApplication extends Application {

	private final WeibosDaoImpl weibosDaoImpl;

	private static final SimpleDateFormat FORMAT1 = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat FORMAT2 = new SimpleDateFormat("yyyyMM");

	public UserInfoApplication() {
		weibosDaoImpl = new WeibosDaoImpl(MybatisConfig.ServerEnum.weibos);
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/{type}/users/{province}/{city}", UserBaseInfoResource.class);
		router.attach("/{type}/users/weibos/{uid}/group/{interval}", UserWeibosInfoResource.class);
		return router;
	}

	/**
	 * 新浪：获取某个地区的用户总量
	 */
	public int getSinaUsersCountByLocation(String tablename, String province, String city) {
		return weibosDaoImpl.getSinaUsersCountByLocation(tablename, province, city);
	}

	/**
	 * 新浪：获取某个地区的用户基本信息
	 */
	public List<SinaUserBaseInfo> getSinaUserInfosByLocation(String tablename, String province, String city, int start,
			int rows) {
		return weibosDaoImpl.getSinaUserInfosByLocation(tablename, province, city, start, rows);
	}

	/**
	 * 腾讯：获取某个地区的用户总量
	 */
	public int getTencentUsersCountByLocation(String tablename, String province, String city) {
		return weibosDaoImpl.getTencentUsersCountByLocation(tablename, province, city);
	}

	/**
	 * 腾讯：获取某个地区的用户基本信息
	 */
	public List<TencentUserBaseInfo> getTencentUserInfosByLocation(String tablename, String province, String city,
			int start, int rows) {
		return weibosDaoImpl.getTencentUserInfosByLocation(tablename, province, city, start, rows);
	}

	/**
	 * 根据时间段统计用户的发博数量
	 */
	public UserWeibosGroup analysisUserWeibosByInterval(String tablename, String uid, String interval) {
		UserWeibosGroup userWeibosGroup = new UserWeibosGroup();
		int count = weibosDaoImpl.selectUserWeiboCount(tablename, uid);
		TreeMap<String, Integer> increment = new TreeMap<>();
		HashMap<String, Integer> daycount = new HashMap<>();
		if ("day".equalsIgnoreCase(interval)) {
			daycount = transList2Map(weibosDaoImpl.selectUserWeibosGroupByInterval(tablename, uid, "yearmonthday", 30));
			increment = initIncrement(daycount, 30);
		} else if ("month".equalsIgnoreCase(interval)) {
			daycount = transList2Map(weibosDaoImpl.selectUserWeibosGroupByInterval(tablename, uid, "yearmonth", 12));
			increment = initIncrement(daycount, 12);
		}
		TreeMap<String, Integer> allcount = initAllcount(increment, count);
		userWeibosGroup.setIncrement(increment);
		userWeibosGroup.setAllcount(allcount);
		return userWeibosGroup;
	}

	private TreeMap<String, Integer> initAllcount(TreeMap<String, Integer> increment, int count) {
		TreeMap<String, Integer> result = new TreeMap<>();
		int t = 0;
		for (Entry<String, Integer> tmp : increment.entrySet()) {
			result.put(tmp.getKey(), count - t);
			t = t - tmp.getValue();
		}
		return result;
	}

	private TreeMap<String, Integer> initIncrement(HashMap<String, Integer> daycount, int c) {
		TreeMap<String, Integer> result = new TreeMap<>();
		long time = System.currentTimeMillis();
		String date;
		for (int i = 0; i < c; i++) {
			if (c == 12) {
				date = FORMAT2.format(new Date(time - 86400_000L * i * 32));
			} else {
				date = FORMAT1.format(new Date(time - 86400_000L * i));
			}
			if (daycount.get(date) == null) {
				result.put(date, 0);
			} else {
				result.put(date, daycount.get(date));
			}
		}
		return result;
	}

	private HashMap<String, Integer> transList2Map(List<WeiboDayCount> data) {
		HashMap<String, Integer> result = new HashMap<>();
		for (WeiboDayCount d : data) {
			result.put(d.getDay(), d.getCount());
		}
		return result;
	}

	public void close() {
		//
	}

}

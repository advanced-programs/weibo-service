package zx.soft.weibo.dao.demo;

import java.util.List;

import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.dao.common.MybatisConfig;
import zx.soft.weibo.dao.domain.sina.WeiboDayCount;
import zx.soft.weibo.dao.weibos.WeibosDaoImpl;

public class WeibosDaoImplDemo {

	public static void main(String[] args) {

		WeibosDaoImpl weibosDaoImpl = new WeibosDaoImpl(MybatisConfig.ServerEnum.weibos);
		/**
		 * 新浪：获取某个地区的用户总量
		 */
		//		System.out.println(sinaWeibos.getSinaUsersCountByLocation("sina_user_baseinfo", "34", "1"));
		/**
		 * 新浪：获取某个地区的用户基本信息
		 */
		//		List<SinaUserBaseInfo> sinaInfos = sinaWeibos.getSinaUserInfosByLocation("sina_user_baseinfo", "34", "1", 3, 2);
		//		System.out.println(JsonUtils.toJson(sinaInfos));
		/**
		 * 腾讯：获取某个地区的用户总量
		 */
		//		System.out.println(sinaWeibos.getTencentUsersCountByLocation("tencent_user_baseinfo", "34", "1"));
		/**
		 * 腾讯：获取某个地区的用户基本信息
		 */
		//		List<TencentUserBaseInfo> tencentInfos = sinaWeibos.getTencentUserInfosByLocation("tencent_user_baseinfo",
		//				"34", "1", 2, 4);
		//		System.out.println(JsonUtils.toJson(tencentInfos));

		//		SimpleWeibosInfo simpleWeibosInfo = new SimpleWeibosInfo.Builder("1123655479866", "112354698", "科技强警")
		//				.setStatusesCount(19233 + "").setWeibo("微博数据内容").setYear("2014").setYearMonth("201412")
		//				.setYearMonthDay("20141208").build();
		//		sinaWeibos.insertSinaUserWeibos(simpleWeibosInfo);
		/**
		 * 获取某个用户当前微博总数
		 */
		//		System.out.println(weibosDaoImpl.selectUserWeiboCount("sina_user_weibos", "1943806713"));
		//		System.out.println(weibosDaoImpl.selectUserWeiboCount("tencent_user_weibos", "wangshi"));
		/**
		 * 按天根据用户名获取分组统计结果（按年/月/日）
		 */
		List<WeiboDayCount> data = weibosDaoImpl.selectUserWeibosGroupByInterval("sina_user_weibos", "1943806713",
				"year", 3);
		System.out.println(JsonUtils.toJson(data));
		data = weibosDaoImpl.selectUserWeibosGroupByInterval("sina_user_weibos", "1943806713", "yearmonth", 12);
		System.out.println(JsonUtils.toJson(data));
		data = weibosDaoImpl.selectUserWeibosGroupByInterval("sina_user_weibos", "1943806713", "yearmonthday", 30);
		System.out.println(JsonUtils.toJson(data));
		//
		data = weibosDaoImpl.selectUserWeibosGroupByInterval("tencent_user_weibos", "wangshi", "year", 3);
		System.out.println(JsonUtils.toJson(data));
		data = weibosDaoImpl.selectUserWeibosGroupByInterval("tencent_user_weibos", "wangshi", "yearmonth", 12);
		System.out.println(JsonUtils.toJson(data));
		data = weibosDaoImpl.selectUserWeibosGroupByInterval("tencent_user_weibos", "wangshi", "yearmonthday", 30);
		System.out.println(JsonUtils.toJson(data));
	}

}

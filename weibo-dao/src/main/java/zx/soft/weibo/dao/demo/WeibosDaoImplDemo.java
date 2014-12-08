package zx.soft.weibo.dao.demo;

import java.util.List;

import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.dao.common.MybatisConfig;
import zx.soft.weibo.dao.domain.sina.WeiboDayCount;
import zx.soft.weibo.dao.weibos.WeibosDaoImpl;

public class WeibosDaoImplDemo {

	public static void main(String[] args) {

		WeibosDaoImpl sinaWeibos = new WeibosDaoImpl(MybatisConfig.ServerEnum.weibos);
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
		System.out.println(sinaWeibos.selectSinaUserWeiboCount("1943806713"));
		/**
		 * 按天根据用户名获取每天分组统计结果
		 */
		List<WeiboDayCount> data = sinaWeibos.selectSinaUserWeibosGroupByDay("1943806713");
		System.out.println(JsonUtils.toJson(data));
		/**
		 * 按天根据用户名获取每月分组统计结果
		 */
		data = sinaWeibos.selectSinaUserWeibosGroupByMonth("1943806713");
		System.out.println(JsonUtils.toJson(data));
	}

}

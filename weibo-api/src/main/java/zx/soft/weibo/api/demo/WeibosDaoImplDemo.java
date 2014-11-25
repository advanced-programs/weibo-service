package zx.soft.weibo.api.demo;

import java.util.List;

import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.api.common.MybatisConfig;
import zx.soft.weibo.api.dao.WeibosDaoImpl;
import zx.soft.weibo.api.domain.SinaUserBaseInfo;
import zx.soft.weibo.api.domain.TencentUserBaseInfo;

public class WeibosDaoImplDemo {

	public static void main(String[] args) {

		WeibosDaoImpl sinaWeibos = new WeibosDaoImpl(MybatisConfig.ServerEnum.weibos);
		/**
		 * 新浪：获取某个地区的用户总量
		 */
		System.out.println(sinaWeibos.getSinaUsersCountByLocation("sina_user_baseinfo", "34", "1"));
		/**
		 * 新浪：获取某个地区的用户基本信息
		 */
		List<SinaUserBaseInfo> sinaInfos = sinaWeibos.getSinaUserInfosByLocation("sina_user_baseinfo", "34", "1", 3, 2);
		System.out.println(JsonUtils.toJson(sinaInfos));
		/**
		 * 腾讯：获取某个地区的用户总量
		 */
		System.out.println(sinaWeibos.getTencentUsersCountByLocation("tencent_user_baseinfo", "34", "1"));
		/**
		 * 腾讯：获取某个地区的用户基本信息
		 */
		List<TencentUserBaseInfo> tencentInfos = sinaWeibos.getTencentUserInfosByLocation("tencent_user_baseinfo",
				"34", "1", 2, 4);
		System.out.println(JsonUtils.toJson(tencentInfos));
	}

}

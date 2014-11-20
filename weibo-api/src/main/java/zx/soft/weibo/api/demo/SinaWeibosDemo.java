package zx.soft.weibo.api.demo;

import java.util.List;

import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.api.common.MybatisConfig;
import zx.soft.weibo.api.domain.SinaUserBaseInfo;
import zx.soft.weibo.api.sina.SinaWeibos;

public class SinaWeibosDemo {

	public static void main(String[] args) {

		SinaWeibos sinaWeibos = new SinaWeibos(MybatisConfig.ServerEnum.weibos);

		/**
		 * 获取某个地区的用户基本信息
		 */
		List<SinaUserBaseInfo> userInfos = sinaWeibos.getUserInfosByLocation("sina_user_baseinfo", 34, 1, 20);
		System.out.println(JsonUtils.toJson(userInfos));
	}

}

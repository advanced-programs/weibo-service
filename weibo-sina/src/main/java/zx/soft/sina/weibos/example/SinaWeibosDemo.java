package zx.soft.sina.weibos.example;

import java.util.List;

import zx.soft.sina.weibos.common.MybatisConfig;
import zx.soft.sina.weibos.domain.SinaUserBaseInfo;
import zx.soft.sina.weibos.persist.SinaWeibos;
import zx.soft.utils.json.JsonUtils;

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

package zx.soft.weibo.dao.demo;

import java.util.Date;

import zx.soft.utils.json.JsonUtils;
import zx.soft.weibo.dao.common.MybatisConfig;
import zx.soft.weibo.dao.domain.sina.WeiboInsertParams;
import zx.soft.weibo.dao.weibos.SinaWeibosDaoImpl;

public class SinaWeibosDaoImplDemo {

	public static void main(String[] args) {
		SinaWeibosDaoImpl sinaWeibosDaoImpl = new SinaWeibosDaoImpl(MybatisConfig.ServerEnum.weibos);
		/**
		 * 插入微博数据表名
		 */
		sinaWeibosDaoImpl.insertTablenames("sina_weibo_tablenames", "sina_weibo_test");
		/**
		 * 读取微博数据表名的数据表
		 */
		System.out.println(sinaWeibosDaoImpl.getTablenames("sina_weibo_tablenames"));
		/**
		 * 插入单条微博数据，需要判断原创微博是否为空
		 */
		WeiboInsertParams weibo = new WeiboInsertParams.Builder("sina_weibo_test", 3771671921647244L, 1649155730L)
				.setRepostscount(100).setCommentscount(101).setAttitudescount(102).setText("测试数据")
				.setCreateat(new Date(System.currentTimeMillis())).setOwid(123456789L).setOusername(987654322L)
				.setFavorited(true).setGeo("安徽 合肥").setLatitude(112.000266).setLongitude(10.22665)
				.setOriginalpic("http://www.baidu.com").setSource("新浪客户端").setVisible("可见").setMlevel(2).build();
		sinaWeibosDaoImpl.insertWeiboInfo(weibo);
		/**
		 * 获取数据表中的最大bid
		 */
		System.out.println(sinaWeibosDaoImpl.getMaxBid("sina_weibo_test"));
		/**
		 * 批量获取微博数据
		 */
		System.out.println(JsonUtils.toJson(sinaWeibosDaoImpl.getWeibosInfo("sina_weibo_test", 1, 10)));
		/**
		 * 获取单条微博数据
		 */
		System.out.println(JsonUtils.toJson(sinaWeibosDaoImpl.getWeiboInfo("sina_weibo_test", 3771671921647244L)));
		/**
		 * 删除单条微博数据
		 */
		sinaWeibosDaoImpl.deleteWeiboInfo("sina_weibo_test", 3771671921647244L);
	}

}

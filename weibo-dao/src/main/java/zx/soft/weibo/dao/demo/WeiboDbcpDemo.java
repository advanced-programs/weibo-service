package zx.soft.weibo.dao.demo;

import zx.soft.weibo.dao.sql.WeiboDbcp;

public class WeiboDbcpDemo {

	public static void main(String[] args) {
		WeiboDbcp weiboDbcp = new WeiboDbcp();
		weiboDbcp.createTablenamesTable("sina_weibo_tablenames");
		weiboDbcp.createSinaUserWeibosTable("sina_weibo_test");
	}

}

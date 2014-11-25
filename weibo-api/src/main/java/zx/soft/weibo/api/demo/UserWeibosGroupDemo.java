package zx.soft.weibo.api.demo;

import zx.soft.weibo.api.domain.UserWeibosGroup;

public class UserWeibosGroupDemo {

	public static void main(String[] args) {

		UserWeibosGroup group = new UserWeibosGroup().instance();
		System.out.println(group.getAllcount());
	}

}

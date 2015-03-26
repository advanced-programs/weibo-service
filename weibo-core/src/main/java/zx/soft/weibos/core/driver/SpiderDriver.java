package zx.soft.weibos.core.driver;

import zx.soft.utils.driver.ProgramDriver;
import zx.soft.weibo.core.weibos.SinaUserWeibosSimple;
import zx.soft.weibos.core.spider.SinaPublicWeibosSpider;

/**
 * 驱动类
 *
 * @author wanggang
 *
 */
public class SpiderDriver {

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("sinaUserWeibosSimple", SinaUserWeibosSimple.class, "新浪公共微博抓取并存储");
			pgd.addClass("sinaPublicWeibosSpider", SinaPublicWeibosSpider.class, "新浪公共微博抓取并存储");
			pgd.driver(args);
			// Success
			exitCode = 0;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

		System.exit(exitCode);

	}

}

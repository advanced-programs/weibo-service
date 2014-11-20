package zx.soft.sina.weibos.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDemo {

	private static Logger logger = LoggerFactory.getLogger(LoggerDemo.class);

	public static void main(String[] args) {
		logger.trace("trace ...");
		logger.debug("debug ...");
		logger.info("info ...");
		logger.warn("warn ...");
		logger.error("error ...");
	}

}

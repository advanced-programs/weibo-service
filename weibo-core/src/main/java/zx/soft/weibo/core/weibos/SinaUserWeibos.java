package zx.soft.weibo.core.weibos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.http.ClientDao;
import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.weibo.dao.common.MybatisConfig;
import zx.soft.weibo.dao.domain.sina.SimpleWeibosInfo;
import zx.soft.weibo.dao.weibos.WeibosDaoImpl;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 新浪用户微博列表类：简略版
 * 
 * @author wgybzb
 *
 */
public class SinaUserWeibos {

	private static Logger logger = LoggerFactory.getLogger(SinaUserWeibos.class);

	private static final SimpleDateFormat FORMAT1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
	private static final SimpleDateFormat FORMAT2 = new SimpleDateFormat("yyyyMMdd");

	private final SinaWeiboAPI api;
	private final WeibosDaoImpl weibosDaoImpl;

	public SinaUserWeibos(ClientDao clientDao) {
		api = new SinaWeiboAPI(clientDao);
		weibosDaoImpl = new WeibosDaoImpl(MybatisConfig.ServerEnum.weibos);
	}

	/**
	 * 测试函数
	 */
	public static void main(String[] args) {
		SinaUserWeibos userWeibos = new SinaUserWeibos(new HttpClientDaoImpl());
		try (BufferedReader br = new BufferedReader(new FileReader(new File("data/uids")));) {
			String str;
			while ((str = br.readLine()) != null) {
				userWeibos.userWeibosToDB(str.split("\\s")[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户的全部微博数据
	 */
	public void userWeibosToDB(String uid) {
		int page = 1;
		SinaDomain weibos, weibo, user;
		SimpleWeibosInfo simpleWeibosInfo;
		DateStr dateStr;
		logger.info("Retriving user:{}'s weibos", uid);
		do {
			logger.info("User:{}'s weibos at page:{}.", uid, page);
			weibos = api.statusesUserTimelineByUid(uid, "0", "0", 100, page, 0, 0, 0);
			if (weibos.getFieldValues("statuses").size() <= 1) {
				break;
			}
			for (Object status : weibos.getFieldValues("statuses")) {
				weibo = (SinaDomain) status;
				user = (SinaDomain) weibo.getFieldValue("user");
				dateStr = getDateStr(weibo.getFieldValue("created_at").toString());
				simpleWeibosInfo = new SimpleWeibosInfo.Builder(weibo.getFieldValue("mid").toString(), user
						.getFieldValue("id").toString(), user.getFieldValue("screen_name").toString())
						.setStatusesCount(user.getFieldValue("statuses_count").toString())
						.setWeibo(weibo.getFieldValue("text").toString()).setYear(dateStr.getYear())
						.setYearMonth(dateStr.getYearMonth()).setYearMonthDay(dateStr.getYearMonthDay()).build();
				try {
					weibosDaoImpl.insertSinaUserWeibos(simpleWeibosInfo);
				} catch (Exception e) {
					logger.error("SQLException:{},text={}", e, weibo.getFieldValue("text").toString());
					try {
						simpleWeibosInfo.setWeibo(new String(weibo.getFieldValue("text").toString().getBytes(), "GBK"));
					} catch (UnsupportedEncodingException e1) {
						throw new RuntimeException(e);
					}
					weibosDaoImpl.insertSinaUserWeibos(simpleWeibosInfo);
				}
			}
			page++;
		} while (weibos.getFieldValues("statuses").size() > 1);
	}

	public static DateStr getDateStr(String str) {
		DateStr dateStr = new DateStr();
		try {
			String date = FORMAT2.format(FORMAT1.parse(str));
			dateStr.setYear(date.substring(0, 4));
			dateStr.setYearMonth(date.substring(0, 6));
			dateStr.setYearMonthDay(date);
			return dateStr;
		} catch (ParseException e) {
			logger.error("ParseException:{}", e);
			throw new RuntimeException(e);
		}
	}

	static class DateStr {

		private String year;
		private String yearMonth;
		private String yearMonthDay;

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getYearMonth() {
			return yearMonth;
		}

		public void setYearMonth(String yearMonth) {
			this.yearMonth = yearMonth;
		}

		public String getYearMonthDay() {
			return yearMonthDay;
		}

		public void setYearMonthDay(String yearMonthDay) {
			this.yearMonthDay = yearMonthDay;
		}

		@Override
		public String toString() {
			return "DateStr:{year=" + year + ",yearMonth=" + yearMonth + ",yearMonthDay=" + yearMonthDay + "}";
		}

	}

}

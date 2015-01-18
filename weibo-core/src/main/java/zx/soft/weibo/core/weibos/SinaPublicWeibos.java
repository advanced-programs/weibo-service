package zx.soft.weibo.core.weibos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.checksum.CheckSumUtils;
import zx.soft.utils.http.ClientDao;
import zx.soft.utils.http.HttpClientDaoImpl;
import zx.soft.utils.json.JsonUtils;
import zx.soft.utils.log.LogbackUtil;
import zx.soft.weibo.dao.domain.sina.WeiboInsertParams;
import zx.soft.weibo.sina.api.SinaWeiboAPI;
import zx.soft.weibo.sina.domain.SinaDomain;

/**
 * 新浪公共微博
 * 
 * @author wanggang
 *
 */
public class SinaPublicWeibos {

	private static Logger logger = LoggerFactory.getLogger(SinaPublicWeibos.class);

	private static final int PUBLIC_WEIBOS_COUNT = 200;

	// 计数器
	private static final AtomicInteger COUNT = new AtomicInteger(0);

	private static final String SINA_PUBLIC_WEIBOS_TABLE = "sina_public_weibos_";

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);

	private static final int MAX_TABLE_NUM = 128;

	private final SinaWeiboAPI api;

	public SinaPublicWeibos(ClientDao clientDao) {
		api = new SinaWeiboAPI(clientDao);
	}

	/**
	 * 测试函数
	 */
	public static void main(String[] args) {
		SinaPublicWeibos publicWeibos = new SinaPublicWeibos(new HttpClientDaoImpl());
		System.out.println(JsonUtils.toJson(publicWeibos));
	}

	/**
	 * 获取公共微博数据，还可以在这里面添加用户信息解析
	 */
	public List<WeiboInsertParams> getPublicWeibos() {
		List<WeiboInsertParams> result = new ArrayList<>();
		SinaDomain weibos, weibo, user, reweibo, reuser;
		WeiboInsertParams weiboInsertParams = null;
		long owid, ousername;
		String originalpic;
		try {
			weibos = api.statusesPublicTimeline(PUBLIC_WEIBOS_COUNT);
			logger.info("Spider sina_public_weibos at:{}, weibos'size={}", COUNT.addAndGet(1),
					weibos.getFieldValues("statuses").size());
			for (Object status : weibos.getFieldValues("statuses")) {
				owid = 0L;
				ousername = 0L;
				originalpic = "";
				// 循环添加RecordInfo
				weibo = (SinaDomain) status;
				user = (SinaDomain) weibo.getFieldValue("user");
				if (weibo.getFieldValue("retweeted_status") != null) {
					reweibo = (SinaDomain) weibo.getFieldValue("retweeted_status");
					reuser = (SinaDomain) reweibo.getFieldValue("user");
					owid = Long.parseLong(reweibo.getFieldValue("id").toString());
					ousername = Long.parseLong(reuser.getFieldValue("id").toString());
				}
				weiboInsertParams = new WeiboInsertParams.Builder(SINA_PUBLIC_WEIBOS_TABLE
						+ CheckSumUtils.getCRC32(weibo.getFieldValue("id").toString()) % MAX_TABLE_NUM,
						Long.parseLong(weibo.getFieldValue("id").toString()), Long.parseLong(user.getFieldValue("id")
								.toString()))
						.setRepostscount(Integer.parseInt(weibo.getFieldValue("reposts_count").toString()))
						.setCommentscount(Integer.parseInt(weibo.getFieldValue("comments_count").toString()))
						.setAttitudescount(Integer.parseInt(weibo.getFieldValue("attitudes_count").toString()))
						.setOwid(owid)
						.setOusername(ousername)
						.setOriginalpic(originalpic)
						.setText(weibo.getFieldValue("text").toString())
						.setCreateat(new Date(getTime(weibo.getFieldValue("created_at").toString())))
						.setFavorited(
								"true".equalsIgnoreCase(weibo.getFieldValue("favorited").toString()) ? Boolean.TRUE
										: Boolean.FALSE).setGeo(weibo.getFieldValue("geo").toString())
						.setLatitude(0.0f).setLongitude(0.0f).setSource(weibo.getFieldValue("source").toString())
						.setVisible(weibo.getFieldValue("visible").toString())
						.setMlevel(Integer.parseInt(weibo.getFieldValue("mlevel").toString())).build();
				result.add(weiboInsertParams);
			}
		} catch (Exception e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
		}
		return result;
	}

	private long getTime(String timeStr) {
		try {
			return FORMAT.parse(timeStr).getTime();
		} catch (ParseException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			// 如果时间解析错误，则使用当前时间
			return System.currentTimeMillis();
		}
	}

}

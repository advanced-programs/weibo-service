package zx.soft.weibo.api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

/**
 * 用户发布的微博，按时间段统计结果
 * 
 * @author wanggang
 *
 */
public class UserWeibosGroup {

	// 增量结果
	private List<List<Object>> increment;
	// 全量结果
	private List<List<Object>> allcount;

	public UserWeibosGroup instance() {
		TreeMap<String, Integer> incrementMap = new TreeMap<>();
		TreeMap<String, Integer> allcountMap = new TreeMap<>();
		Random random = new Random();
		for (int i = 1; i <= 9; i++) {
			incrementMap.put("2014-0" + i, random.nextInt(200));
			allcountMap.put("2014-0" + i, random.nextInt(200));
		}
		incrementMap.put("2014-10", random.nextInt(100));
		allcountMap.put("2014-10", random.nextInt(100));
		this.setIncrement(incrementMap);
		this.setAllcount(allcountMap);
		return this;
	}

	public List<List<Object>> getIncrement() {
		return increment;
	}

	public void setIncrement(TreeMap<String, Integer> increment) {
		this.increment = trans(increment);
	}

	public List<List<Object>> getAllcount() {
		return allcount;
	}

	public void setAllcount(TreeMap<String, Integer> allcount) {
		this.allcount = trans(allcount);
	}

	private List<List<Object>> trans(TreeMap<String, Integer> map) {
		List<List<Object>> result = new ArrayList<>();
		for (Entry<String, Integer> tmp : map.entrySet()) {
			List<Object> list = new ArrayList<>();
			list.add(tmp.getKey());
			list.add(tmp.getValue());
			result.add(list);
		}
		return result;
	}

}

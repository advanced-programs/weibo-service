package zx.soft.weibo.api.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 相应HighCharts可处理的数据模型
 * 
 * @author wanggang
 *
 */
public class ResponseJSFormat {

	private final List<Object> categories = new ArrayList<>();

	private final List<NameDataPair> series = new ArrayList<>();

	public void setCategories(Object object) {
		this.categories.add(object);
	}

	public void setSeries(NameDataPair nameDataPair) {
		this.series.add(nameDataPair);
	}

	public List<Object> getCategories() {
		return categories;
	}

	public List<NameDataPair> getSeries() {
		return series;
	}

	public static class NameDataPair {

		private Object name;
		private final List<Object> data = new ArrayList<>();

		public Object getName() {
			return name;
		}

		public void setName(Object name) {
			this.name = name;
		}

		public List<Object> getData() {
			return data;
		}

		public void setData(Object object) {
			this.data.add(object);
		}

	}

}

package zx.soft.weibo.sina.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import zx.soft.weibo.sina.common.NamedList;

/**
 * 动态数据模型类
 * 
 * @author wanggang
 *
 */
public class SinaDomain implements Map<String, Object>, Iterable<Map.Entry<String, Object>>, Serializable {

	private static final long serialVersionUID = -2083654392208323215L;

	private final Map<String, Object> _fields;

	public SinaDomain() {
		_fields = new LinkedHashMap<String, Object>();
	}

	/**
	 * @return 数据模型的Field名称列表 - 直接通过SinaDomain后台获取的集合.
	 * @see #keySet
	 */
	public Collection<String> getFieldNames() {
		return this.keySet();
	}

	///////////////////////////////////////////////////////////////////
	// 增加 / 设置 / 删除 Fields
	///////////////////////////////////////////////////////////////////

	/**
	 * 从Domain中删除所有的fileds
	 */
	@Override
	public void clear() {
		_fields.clear();
	}

	/**
	 * 通过指定name删除所有fields
	 */
	public boolean removeFields(String name) {
		return this.remove(name) != null;
	}

	/**
	 * 通过给定的对象和name设置一个filed
	 * 如果对象是数据，则设置多个filed;设置时会替换与name相同的所有fields。
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setField(String name, Object value) {
		if (value instanceof Object[]) {
			value = new ArrayList(Arrays.asList((Object[]) value));
		} else if (value instanceof Collection) {
			// nothing
		} else if (value instanceof NamedList) {
			// nothing
		} else if (value instanceof Iterable) {
			ArrayList<Object> lst = new ArrayList<Object>();
			for (Object o : (Iterable) value) {
				lst.add(o);
			}
			value = lst;
		}
		_fields.put(name, value);
	}

	/**
	 * 增加一个field到Domain中
	 * 如果存在一个名为name的field，则往集合中添加该field；
	 * 如果value是一个集合类，那么每个值都会被添加进来。
	 */
	@SuppressWarnings("unchecked")
	public void addField(String name, Object value) {
		Object existing = _fields.get(name);
		if (existing == null) {
			if (value instanceof Collection) {
				Collection<Object> c = new ArrayList<Object>(3);
				for (Object o : (Collection<Object>) value) {
					c.add(o);
				}
				this.setField(name, c);
			} else {
				this.setField(name, value);
			}
			return;
		}

		Collection<Object> vals = null;
		if (existing instanceof Collection) {
			vals = (Collection<Object>) existing;
		} else {
			vals = new ArrayList<Object>(3);
			vals.add(existing);
		}

		// 添加所有的值到集合类中
		if (value instanceof Iterable) {
			for (Object o : (Iterable<Object>) value) {
				vals.add(o);
			}
		} else if (value instanceof Object[]) {
			for (Object o : (Object[]) value) {
				vals.add(o);
			}
		} else {
			vals.add(value);
		}
		_fields.put(name, vals);
	}

	///////////////////////////////////////////////////////////////////
	// 获取field对应的值
	///////////////////////////////////////////////////////////////////

	/**
	 * 返回一个field的第一个值
	 */
	@SuppressWarnings("rawtypes")
	public Object getFirstValue(String name) {
		Object v = _fields.get(name);
		if (v == null || !(v instanceof Collection))
			return v;
		Collection c = (Collection) v;
		if (c.size() > 0) {
			return c.iterator().next();
		}
		return null;
	}

	/**
	 * 返回一个field的值或者值的集合
	 */
	public Object getFieldValue(String name) {
		return _fields.get(name);
	}

	/**
	 * 返回一个filed的值的集合
	 */
	@SuppressWarnings("unchecked")
	public Collection<Object> getFieldValues(String name) {
		Object v = _fields.get(name);
		if (v instanceof Collection) {
			return (Collection<Object>) v;
		}
		if (v != null) {
			ArrayList<Object> arr = new ArrayList<Object>(1);
			arr.add(v);
			return arr;
		}
		return null;
	}

	@Override
	public String toString() {
		return "SolrDocument" + _fields;
	}

	/**
	 * 返回fields的集合迭代器
	 */
	@Override
	public Iterator<Entry<String, Object>> iterator() {
		return _fields.entrySet().iterator();
	}

	//-----------------------------------------------------------------------------------------
	// JSTL Helpers
	//-----------------------------------------------------------------------------------------

	/**
	 * 获取SinaDomain的filed-value集合的Map接口/形式
	 */
	public Map<String, Collection<Object>> getFieldValuesMap() {
		return new Map<String, Collection<Object>>() {
			/** 获取field-Value */
			@Override
			public Collection<Object> get(Object key) {
				return getFieldValues((String) key);
			}

			// 支持的方法
			@Override
			public boolean containsKey(Object key) {
				return _fields.containsKey(key);
			}

			@Override
			public Set<String> keySet() {
				return _fields.keySet();
			}

			@Override
			public int size() {
				return _fields.size();
			}

			@Override
			public boolean isEmpty() {
				return _fields.isEmpty();
			}

			// 不支持的操作，这些对于JSTL没有必要
			@Override
			public void clear() {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean containsValue(Object value) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Set<java.util.Map.Entry<String, Collection<Object>>> entrySet() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void putAll(Map<? extends String, ? extends Collection<Object>> t) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Collection<Collection<Object>> values() {
				throw new UnsupportedOperationException();
			}

			@Override
			public Collection<Object> put(String key, Collection<Object> value) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Collection<Object> remove(Object key) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String toString() {
				return _fields.toString();
			}
		};
	}

	/**
	 * 获取SinaDomain的fileds的Map接口/形式，对于JSTL很有用
	 */
	public Map<String, Object> getFieldValueMap() {

		return new Map<String, Object>() {
			/** 获取field-Value */
			@Override
			public Object get(Object key) {
				return getFirstValue((String) key);
			}

			// 支持的方法
			@Override
			public boolean containsKey(Object key) {
				return _fields.containsKey(key);
			}

			@Override
			public Set<String> keySet() {
				return _fields.keySet();
			}

			@Override
			public int size() {
				return _fields.size();
			}

			@Override
			public boolean isEmpty() {
				return _fields.isEmpty();
			}

			// 不支持的操作，这些对于JSTL没有必要
			@Override
			public void clear() {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean containsValue(Object value) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Set<java.util.Map.Entry<String, Object>> entrySet() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void putAll(Map<? extends String, ? extends Object> t) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Collection<Object> values() {
				throw new UnsupportedOperationException();
			}

			@Override
			public Collection<Object> put(String key, Object value) {
				throw new UnsupportedOperationException();
			}

			@Override
			public Collection<Object> remove(Object key) {
				throw new UnsupportedOperationException();
			}

			@Override
			public String toString() {
				return _fields.toString();
			}
		};
	}

	//---------------------------------------------------
	// MAP接口
	//---------------------------------------------------

	@Override
	public boolean containsKey(Object key) {
		return _fields.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return _fields.containsValue(value);
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return _fields.entrySet();
	}

	//TODO: 输入参数不应该是个String型？这里的_fileds映射需要String类型，所以可以把Object改成String。
	@Override
	public Object get(Object key) {
		return _fields.get(key);
	}

	@Override
	public boolean isEmpty() {
		return _fields.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return _fields.keySet();
	}

	@Override
	public Object put(String key, Object value) {
		return _fields.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> t) {
		_fields.putAll(t);
	}

	@Override
	public Object remove(Object key) {
		return _fields.remove(key);
	}

	@Override
	public int size() {
		return _fields.size();
	}

	@Override
	public Collection<Object> values() {
		return _fields.values();
	}

}

package zx.soft.weibo.mapred.domain;

import java.io.Serializable;

public class Visible implements Serializable {

	private static final long serialVersionUID = -6412677856067543378L;

	// 类型
	private int type;
	// 列表ID
	private int list_id;

	public Visible() {
		super();
	}

	public Visible(int type, int list_id) {
		super();
		this.type = type;
		this.list_id = list_id;
	}

	@Override
	public String toString() {
		return "Visible [type=" + type + ", list_id=" + list_id + "]";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

}

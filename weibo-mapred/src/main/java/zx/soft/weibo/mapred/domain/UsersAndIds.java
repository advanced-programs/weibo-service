package zx.soft.weibo.mapred.domain;

import java.io.Serializable;
import java.util.List;

public class UsersAndIds implements Serializable {

	private static final long serialVersionUID = 6388871380117667561L;

	private List<User> users;
	private List<String> ids;

	public UsersAndIds() {
		super();
	}

	public UsersAndIds(List<User> users, List<String> ids) {
		super();
		this.users = users;
		this.ids = ids;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

}

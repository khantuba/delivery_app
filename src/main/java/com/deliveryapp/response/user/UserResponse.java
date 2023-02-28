package com.deliveryapp.response.user;

import java.util.List;

import com.deliveryapp.bean.user.UserBean;
import com.deliveryapp.response.Response;

/**
 * This class contains response entities for user controller
 * 
 * @author mohd.shadab
 */
public class UserResponse extends Response {

	/** The list of UserBean */
	private List<UserBean> users;

	public List<UserBean> getUsers() {
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

}

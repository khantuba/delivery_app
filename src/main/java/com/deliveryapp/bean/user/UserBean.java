package com.deliveryapp.bean.user;

/**
 * This class contains the User Bean
 * 
 * @author mohd.shadab
 */
public class UserBean {
	
	/** The email id */
	private String email;
	
	/** The userDetails */
	private UserDetailsBean userDetails;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDetailsBean getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsBean userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "UserBean [email=" + email + ", userDetails=" + userDetails + "]";
	}
}

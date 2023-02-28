package com.deliveryapp.bean.user;

/**
 * This class contains the User Details Bean
 * 
 * @author mohd.shadab
 */
public class UserDetailsBean {
	
	/** The First Name */
	private String firstName;
	
	/** The Last Name */
	private String lastName;
	
	/** The designation */
	private String designation;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "UserDetailsBean [firstName=" + firstName + ", lastName=" + lastName + ", designation=" + designation
				+ "]";
	}
}

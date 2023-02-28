package com.deliveryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryapp.bean.user.UserBean;
import com.deliveryapp.constants.Constants;
import com.deliveryapp.response.user.UserResponse;
import com.deliveryapp.service.UserService;

/**
 * This class contains the user controller for interacting with the database
 * 
 * @author mohd.shadab
 */
@RestController
public class UserController {

	/** The userService */
	@Autowired
	UserService userService;

	/**
	 * This controller will add the user
	 * 
	 * @param userBean : The user data in the JSON format
	 * @return UserResponse : The user response in the JSON format
	 */
	@PostMapping(path = "/user/addUser", consumes = Constants.CONSTANT_APPLICATION_JSON, produces = Constants.CONSTANT_APPLICATION_JSON)
	public UserResponse addUser(@RequestBody UserBean userBean) {
		return userService.createUser(userBean);
	}

	/**
	 * This controller will get the user from email
	 * 
	 * @param email : The user's email
	 * @return UserResponse : The user response in the JSON format
	 */
	@GetMapping(path = "/user/getUser", consumes = Constants.CONSTANT_APPLICATION_JSON, produces = Constants.CONSTANT_APPLICATION_JSON)
	public UserResponse getUser(@RequestParam String email) {
		return userService.getUser(email);
	}

	/**
	 * This controller will get all the users
	 * 
	 * @return UserResponse : The users response in the JSON format
	 */
	@GetMapping(path = "/user/getUsers", consumes = Constants.CONSTANT_APPLICATION_JSON, produces = Constants.CONSTANT_APPLICATION_JSON)
	public UserResponse getUsers() {
		return userService.getUsers();
	}

	/**
	 * This controller will update the existing user
	 * 
	 * @param userBean : The user data in the JSON format
	 * @return userResponse : The users response in the JSON format
	 */
	@PutMapping(path = "/user/updateUser", consumes = Constants.CONSTANT_APPLICATION_JSON, produces = Constants.CONSTANT_APPLICATION_JSON)
	public UserResponse updateUser(@RequestBody UserBean userBean) {
		return userService.updateUser(userBean);
	}

	/**
	 * This controller will delete the existing user
	 * 
	 * @param email : The user's email
	 * @return userResponse : The users response in the JSON format
	 */
	@DeleteMapping(path = "/user/deleteUser", consumes = Constants.CONSTANT_APPLICATION_JSON, produces = Constants.CONSTANT_APPLICATION_JSON)
	public UserResponse deleteUser(@RequestParam String email) {
		return userService.deleteUser(email);
	}
}

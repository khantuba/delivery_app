package com.deliveryapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.deliveryapp.constants.DbConstants;
import com.deliveryapp.controller.UserController;
import com.deliveryapp.bean.user.UserBean;
import com.deliveryapp.bean.user.UserDetailsBean;
import com.deliveryapp.constants.ApiMessages;
import com.deliveryapp.constants.Constants;
import com.deliveryapp.response.ApiErrorCode;
import com.deliveryapp.response.user.UserResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

/**
 * This is service class for the {@link UserController} and have functionality
 * to connect direct with database
 * 
 * @author mohd.shadab
 */

@Service
public class UserService {

	/**
	 * This method creates the user in database with document id as email
	 * 
	 * @param userBean : {@link UserBean}
	 * @return UserResponse : {@link UserResponse}
	 */
	public UserResponse createUser(UserBean userBean) {
		UserResponse response = new UserResponse();
		setDefaultResponse(response);
		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection(DbConstants.COLLECTION_USERS)
				.document(userBean.getEmail()).set(userBean.getUserDetails());
		try {
			collectionsApiFuture.get().getUpdateTime().toString();
			setResponse(response, ApiMessages.USER_CREATION_MSG);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		return response;
	}

	/**
	 * This method will get the user from database by key id email
	 * 
	 * @param email : string
	 * @return UserResponse : {@link UserResponse}
	 */
	public UserResponse getUser(String email) {
		UserResponse response = new UserResponse();
		setDefaultResponse(response);
		Firestore dbFireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFireStore.collection(DbConstants.COLLECTION_USERS).document(email);
		ApiFuture<DocumentSnapshot> collectionApiFuture = documentReference.get();
		try {
			DocumentSnapshot document = collectionApiFuture.get();
			response.setRespCode(Constants.CONSTANT_ZERO);
			response.setRespMsg(Constants.CONSTANT_SUCCESS_RESP);
			List<UserBean> users = new ArrayList<>();
			UserBean userBean = new UserBean();
			if (document.exists()) {
				/** Creating the user bean */
				userBean.setEmail(email);
				userBean.setUserDetails(document.toObject(UserDetailsBean.class));
				users.add(userBean);
				/** Creating the Response */
				response.setApiErrorCode(ApiErrorCode.ERROR_CODE_SUCCESS);
				response.setMessage(ApiMessages.USER_FETCHED_MSG);
				response.setUsers(users);
			} else {
				response.setApiErrorCode(ApiErrorCode.ERROR_CODE_FAILURE);
				response.setMessage(ApiMessages.USER_NOT_FETCHED_MSG);
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		return response;
	}

	/**
	 * This method will get all the users from the database
	 * 
	 * @return UserResponse : {@link UserResponse}
	 */
	public UserResponse getUsers() {
		UserResponse response = new UserResponse();
		setDefaultResponse(response);
		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> collectionApiFuture = dbFireStore.collection(DbConstants.COLLECTION_USERS).get();
		try {
			List<QueryDocumentSnapshot> documents = collectionApiFuture.get().getDocuments();
			setResponse(response, ApiMessages.USERS_FETCHED_MSG);
			List<UserBean> users = new ArrayList<>();
			/** Getting all the Users and adding it in the list */
			for (QueryDocumentSnapshot document : documents) {
				UserBean userBean = new UserBean();
				userBean.setEmail(document.getId());
				userBean.setUserDetails(document.toObject(UserDetailsBean.class));
				users.add(userBean);
			}
			if (users.isEmpty()) {
				response.setApiErrorCode(ApiErrorCode.ERROR_CODE_FAILURE);
				response.setMessage(ApiMessages.USERS_NOT_FETCHED_MSG);
			}
			response.setUsers(users);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		return response;
	}

	/**
	 * This method will update the user if it exist
	 * 
	 * @param userBean : {@link UserBean}
	 * @return UserResponse : {@link UserResponse}
	 */
	public UserResponse updateUser(UserBean userBean) {
		UserResponse response;
		/** Pulling out the user from the database with email as key */
		response = getUser(userBean.getEmail());
		/** User is present then it will go in the database for updating */
		if (response.getApiErrorCode() == ApiErrorCode.ERROR_CODE_SUCCESS) {
			setDefaultResponse(response);
			Firestore dbFireStore = FirestoreClient.getFirestore();
			ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection(DbConstants.COLLECTION_USERS)
					.document(userBean.getEmail()).set(userBean.getUserDetails());
			try {
				collectionsApiFuture.get().getUpdateTime().toString();
				List<UserBean> users = new ArrayList<>();
				users.add(userBean);
				setResponse(response, ApiMessages.USER_UPDATION_MSG);
				response.setUsers(users);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		} else {
			response.setMessage(ApiMessages.USER_NOT_FETCHED_MSG);
		}
		return response;
	}

	/**
	 * This method will delete the user if it exist
	 * 
	 * @param email : String
	 * @return UserResponse : {@link UserResponse}
	 */
	public UserResponse deleteUser(String email) {
		UserResponse response;
		/** Pulling out the user from the database with email as key */
		response = getUser(email);
		/** User is present then it will go in the database for deleting */
		if (response.getApiErrorCode() == ApiErrorCode.ERROR_CODE_SUCCESS) {
			setDefaultResponse(response);
			Firestore dbFireStore = FirestoreClient.getFirestore();
			ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection(DbConstants.COLLECTION_USERS)
					.document(email).delete();
			try {
				collectionsApiFuture.get().getUpdateTime().toString();
				setResponse(response, ApiMessages.USER_DELETION_MSG);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		} else {
			response.setMessage(ApiMessages.USER_NOT_FETCHED_MSG);
		}

		return response;
	}

	/**
	 * Method to set the Response to default state or failure state
	 * 
	 * @param response : {@link UserResponse}
	 */
	private void setDefaultResponse(UserResponse response) {
		response.setRespMsg(Constants.CONSTANT_FAILURE_RESP);
		response.setRespCode(Constants.CONSTANT_MINUS_ONE);
		response.setApiErrorCode(ApiErrorCode.ERROR_CODE_FAILURE);
		response.setMessage(null);
	}

	/**
	 * Method to set the Response to the success state along with the message that
	 * need to be passes to end-user
	 * 
	 * @param response : {@link UserResponse}
	 * @param msg      : String
	 */
	private void setResponse(UserResponse response, String msg) {
		response.setRespMsg(Constants.CONSTANT_SUCCESS_RESP);
		response.setRespCode(Constants.CONSTANT_ZERO);
		response.setApiErrorCode(ApiErrorCode.ERROR_CODE_SUCCESS);
		response.setMessage(msg);
	}
}

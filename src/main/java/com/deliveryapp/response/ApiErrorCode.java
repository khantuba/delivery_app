package com.deliveryapp.response;

/**
 * This class contains the ApiErrorCode
 * 
 * @author mohd.shadab
 */
public class ApiErrorCode {

	/** The failure error code for API 1100 */
	public static final int ERROR_CODE_FAILURE = 1100;

	/** The success error Code for API 1101 */
	public static final int ERROR_CODE_SUCCESS = ERROR_CODE_FAILURE + 1;

	/** The private Constructor */
	private ApiErrorCode() {
	}

}

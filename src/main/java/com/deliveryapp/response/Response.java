package com.deliveryapp.response;

/**
 * This class contains the general Response that need to be added with every
 * response
 * 
 * @author mohd.shadab
 */

public class Response {

	/** The response message */
	private String respMsg;

	/** The response code */
	private int respCode;

	/** The API error code */
	private int apiErrorCode;

	/** Message from the particular API */
	private String message;

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public int getApiErrorCode() {
		return apiErrorCode;
	}

	public void setApiErrorCode(int apiErrorCode) {
		this.apiErrorCode = apiErrorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

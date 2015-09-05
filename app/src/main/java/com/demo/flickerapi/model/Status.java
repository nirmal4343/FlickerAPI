package com.demo.flickerapi.model;

/**
 * <h1> Status  </h1>
 * Satus wrapper class to encapsulate the JSON API response object
 *
 * @author  Nirmal Thakur
 * @version 1.0
 * @Date 9/4/2015
 */

public class Status {

    private String status;

	private String message;

	private FlickerApiResponse flickerApiResponse;

	public static final String STATUS_SUCCESS = "success";

    public static final String STATUS_FAIL = "error";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FlickerApiResponse getFlickerApiResponse() {
		return flickerApiResponse;
	}

	public void setFlickerApiResponse(FlickerApiResponse flickerApiResponse) {
		this.flickerApiResponse = flickerApiResponse;
	}
}

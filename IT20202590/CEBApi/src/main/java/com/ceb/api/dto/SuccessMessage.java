package com.ceb.api.dto;

public class SuccessMessage {
	private String statusCode;
	private String statusMessage;
	private String timestamp;

	public SuccessMessage() {
		super();

	}

	public SuccessMessage(String statusCode, String statusMessage, String timestamp) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.timestamp = timestamp;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}

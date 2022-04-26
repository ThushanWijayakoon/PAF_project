package com.ceb.api.dto;

public class ErrorMessage {
	private String statusMessage;
	private String statusCode;
	private String timestamp;
	private String type;

	public ErrorMessage() {
		super();

	}

	public ErrorMessage(String statusMessage, String statusCode, String timestamp, String type) {
		super();
		this.statusMessage = statusMessage;
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.type = type;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

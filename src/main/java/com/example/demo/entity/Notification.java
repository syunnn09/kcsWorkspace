package com.example.demo.entity;

public enum Notification {

	PHONE("電話メモがあります", "/portal/phone");

	private String message;
	private String path;
	Notification(String message, String path) {
		this.message = message;
		this.path = path;
	}

	public String getMessage() {
		return this.message;
	}

	public String getPath() {
		return this.path;
	}
}

package com.example.demo.entity;

public enum Notification {

	PHONE("電話メモがあります", "/portal/phone"),
	ATWORK("現在勤務中です", "/portal/timecard"),
	REST("現在休憩中です", "/portal/timecard");

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

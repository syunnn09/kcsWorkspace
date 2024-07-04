package com.example.demo.controller;

public enum Status {
	SUCCESS("success"),
	FAILED("failed");

	public String status;
	Status(String status) {
		this.status = status;
	}
	public static Status getStatus(boolean bool) {
		return bool ? SUCCESS : FAILED;
	}
}
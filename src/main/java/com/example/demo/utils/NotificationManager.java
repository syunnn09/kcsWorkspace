package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.PhoneRepository;

import jakarta.servlet.http.HttpSession;

public class NotificationManager {

	@Autowired
	PhoneRepository phoneRepository;

	@Autowired
	HttpSession session;

	public void get() {
		
	}
}

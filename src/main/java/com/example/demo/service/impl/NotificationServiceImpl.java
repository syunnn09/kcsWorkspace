package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationCrudRepository;
import com.example.demo.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationCrudRepository repo;

	@Override
	public List<Notification> getNotification(String userid) {
		return repo.getNotification(userid);
	}

}

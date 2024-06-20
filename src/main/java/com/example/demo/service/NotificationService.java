package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Notification;

public interface NotificationService {

	public List<Notification> getNotification(String usreid);
}

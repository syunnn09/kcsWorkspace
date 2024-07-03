package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Notification;
import com.example.demo.entity.Phone;
import com.example.demo.entity.User;
import com.example.demo.repository.PhoneRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class NotificationManager {

	@Autowired
	PhoneRepository phoneRepository;

	@Autowired
	HttpSession session;

	private User getUser() {
		return (User) session.getAttribute("user");
	}

	public List<Notification> getNotifications() {
		List<Notification> notifications = new ArrayList<>();

		List<Phone> phones = phoneRepository.findByToIsAndCheckedIs(getUser().getUserid(), 0);
		if (phones.size() != 0) {
			Notification notification = Notification.PHONE;
			notifications.add(notification);
		}

		return notifications;
	}
}

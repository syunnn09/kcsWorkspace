package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.consts.Const.TimecardStatus;
import com.example.demo.entity.Notification;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Timecard;
import com.example.demo.entity.User;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.service.PortalService;

import jakarta.servlet.http.HttpSession;

@Service
public class NotificationManager {

	@Autowired
	PhoneRepository phoneRepository;

	@Autowired
	PortalService portalService;

	@Autowired
	HttpSession session;

	private User getUser() {
		return (User) session.getAttribute("user");
	}

	public List<Notification> getNotifications() {
		User user = getUser();
		List<Notification> notifications = new ArrayList<>();

		List<Phone> phones = phoneRepository.findByToIsAndCheckedIs(user.getUserid(), 0);
		if (phones.size() != 0) {
			Notification notification = Notification.PHONE;
			notifications.add(notification);
		}

		Timecard card = portalService.getTimecard(user.getUserid());
		if (card != null) {
			if (card.getStatus().equals(TimecardStatus.ATWORK.getStatus())) {
				Notification notification = Notification.ATWORK;
				notifications.add(notification);
			} else if (card.getStatus().equals(TimecardStatus.REST.getStatus())) {
				Notification notification = Notification.REST;
				notifications.add(notification);
			}
		}

		return notifications;
	}
}

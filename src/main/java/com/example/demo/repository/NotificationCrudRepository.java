package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Notification;

public interface NotificationCrudRepository extends CrudRepository<Notification, Integer> {

	@Query("SELECT id, message, isRead FROM notification WHERE userid=:userid AND isRead=0")
	public List<Notification> getNotification(String userid);
}

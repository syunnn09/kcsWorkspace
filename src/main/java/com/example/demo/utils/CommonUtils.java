package com.example.demo.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Notification;

public class CommonUtils {

	public static List<Notification> getSampleNotification() {
		List<Notification> notifications = new ArrayList<>();
		notifications.add(new Notification(1, "新着メッセージがあります", false));
		notifications.add(new Notification(1, "電話メモがあります", false));
		return notifications;
	}

	public static LocalDate getStartDate() {
		return getStartDate(0);
	}

	public static LocalDate getStartDate(int ad) {
		ad += 1;
		return getDate(DayOfWeek.SUNDAY).minusDays(ad*7);
	}

	public static LocalDate getEndDate() {
		return getDate(DayOfWeek.SATURDAY);
	}

	public static LocalDate getDate(DayOfWeek dayOfWeek) {
		LocalDate now = LocalDate.now();
		return now.with(dayOfWeek);
	}

	public static LocalDate convertDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static int getStartDiff(DayOfWeek dayOfWeek) {
		return switch (dayOfWeek) {
			case SUNDAY -> 0;
			case MONDAY -> 1;
			case TUESDAY -> 2;
			case WEDNESDAY -> 3;
			case THURSDAY -> 4;
			case FRIDAY -> 5;
			case SATURDAY -> 6;
			default -> 0;
		};
	}
}

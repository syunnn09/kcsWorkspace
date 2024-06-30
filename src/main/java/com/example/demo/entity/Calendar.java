package com.example.demo.entity;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.utils.CommonUtils;

import lombok.Data;

@Data
public class Calendar {

	private List<Integer> datesOfMonth;
	private List<String> scheduleList;

	public Calendar() {
		init(YearMonth.now());
	}

	public Calendar(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		YearMonth yearMonth = YearMonth.parse(date, formatter);
		init(yearMonth);
	}

	private void init(YearMonth yearMonth) {
		LocalDate firstDayOfMonth = yearMonth.atDay(1);
		LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

		datesOfMonth = new ArrayList<>();
		scheduleList = new ArrayList<>();

		scheduleList.add("Meeting with Client A");
		scheduleList.add("Team Lunch");
		scheduleList.add("Conference Call");

		int diff = CommonUtils.getStartDiff(firstDayOfMonth.getDayOfWeek());
		for (int i = 0; i < diff; i++) {
			datesOfMonth.add(0);
		}
		for (int i = 1; i <= lastDayOfMonth.getDayOfMonth(); i++) {
			datesOfMonth.add(i);
		}
		diff = 6 - CommonUtils.getStartDiff(lastDayOfMonth.getDayOfWeek());
		for (int i = 0; i < diff; i++) {
			datesOfMonth.add(0);
		}
	}

	public List<List<Integer>> week() {
		List<List<Integer>> weeks = new ArrayList<>();
		for (int i = 0; i < datesOfMonth.size() / 7; i++) {
			weeks.add(new ArrayList<>());
			for (int j = 0; j < 7; j++) {
				weeks.get(i).add(datesOfMonth.get(i*7 + j));
			}
		}
		return weeks;
	}
}

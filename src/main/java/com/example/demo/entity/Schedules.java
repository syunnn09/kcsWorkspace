package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.utils.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedules {

	HashMap<String, ScheduleRow> scheduleMap;
	String[] days = new String[7];

	public Schedules(List<Schedule> schedule) {
		this(schedule, 0);
	}

	public Schedules(List<Schedule> schedule, int ad) {
		LocalDate start = CommonUtils.getStartDate(ad);
		scheduleMap = new HashMap<String, ScheduleRow>();
		for (int i = 0; i < 7; i++) {
			String day = start.plusDays(i).toString();
			days[i] = day;
			scheduleMap.put(day, new ScheduleRow());
		}
		schedule.forEach(s -> this.add(s));
	}

	public ScheduleRow find(LocalDate date) {
		return scheduleMap.get(date.toString());
	}

	public void add(Schedule schedule) {
		this.add(schedule.getDate(), schedule);
	}

	public void add(LocalDate date, Schedule schedule) {
		ScheduleRow row = find(date);
		if (row != null) {
			row.add(schedule);
		}
	}

	public List<Map<String, ScheduleRow>> getSchedules() {
		List<Map<String, ScheduleRow>> schedules = new ArrayList<>();
		for (String day : days) {
			Map<String, ScheduleRow> map = new HashMap<>();
			map.put(day, this.scheduleMap.get(day));
			schedules.add(map);
		}
		return schedules;
	}
}

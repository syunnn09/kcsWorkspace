package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleRow {

	List<Schedule> schedules;

	public ScheduleRow() {
		this.schedules = new ArrayList<>();
	}

	public void add(Schedule schedule) {
		this.schedules.add(schedule);
	}
}

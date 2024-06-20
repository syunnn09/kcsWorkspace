package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Schedule;
import com.example.demo.form.ScheduleForm;

public interface ScheduleService {

	public List<Schedule> getPersonalSchedule(String userid, int ad);
	public List<Schedule> getPersonalSchedule(String userid);

	public List<Schedule> getTeamSchedule(int departmentId, int ad);
	public List<Schedule> getTeamSchedule(int departmentId);

	public void save(ScheduleForm form, String userid);
	public void save(Schedule schedule);
}

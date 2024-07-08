package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Schedule;
import com.example.demo.form.ScheduleForm;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.service.ScheduleService;
import com.example.demo.utils.CommonUtils;

public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleRepository repo;

	@Override
	public List<Schedule> getPersonalSchedule(String userid) {
		return this.getPersonalSchedule(userid, 0);
	}

	@Override
	public List<Schedule> getPersonalSchedule(String userid, int ad) {
		LocalDate start = CommonUtils.getStartDate(ad);
		LocalDate end = CommonUtils.getEndDate(start);
		return repo.getPersonalSchedule(userid, start.toString(), end.toString());
	}

	@Override
	public List<Schedule> getTeamSchedule(int departmentId, int ad) {
		LocalDate start = CommonUtils.getStartDate(ad);
		LocalDate end = CommonUtils.getEndDate(start);
		return repo.getTeamSchedule(departmentId, start.toString(), end.toString());
	}

	@Override
	public List<Schedule> getTeamSchedule(int departmentId) {
		return this.getTeamSchedule(departmentId, 0);
	}

	@Override
	public void save(ScheduleForm form, String userid) {
		Schedule schedule = new Schedule(form, userid);
		this.save(schedule);
	}

	@Override
	public void save(Schedule schedule) {
		repo.save(schedule);
	}

	@Override
	public void update(ScheduleForm form) {
		Schedule schedule = new Schedule(form);
		repo.save(schedule);
	}
}

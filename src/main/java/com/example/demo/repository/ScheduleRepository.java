package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

	@Query("SELECT * FROM schedule WHERE userid = :userid AND isPersonal=1 AND date BETWEEN :start AND :end")
	public List<Schedule> getPersonalSchedule(String userid, String start, String end);

	@Query("SELECT * FROM schedule INNER JOIN users ON schedule.userid = users.userid WHERE departmentId = :departmentId AND isTeam = 1 AND date BETWEEN :start AND :end")
	public List<Schedule> getTeamSchedule(int departmentId, String start, String end);
}

package com.example.demo.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;

import com.example.demo.entity.Schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleForm {

	@Id
	public int id;
	public String title;
	public String status;
	public String date;
	public String startTime;
	public String endTime;
	public String place;
	public String detail;
	public boolean isPersonal;
	public boolean isTeam;
	public String userid;

	public ScheduleForm(Schedule schedule) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.status = schedule.getStatus();
		this.date = getDate(schedule.getDate());
		this.startTime = schedule.getStartTime();
		this.endTime = schedule.getEndTime();
		this.place = schedule.getPlace();
		this.detail = schedule.getDetail();
		this.isPersonal = schedule.isPersonal();
		this.isTeam = schedule.isTeam();
		this.userid = schedule.getUserid();
	}

	private String getDate(LocalDate time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return time.format(formatter);
	}
}

package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import com.example.demo.form.ScheduleForm;
import com.example.demo.utils.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

	public Schedule(ScheduleForm form, String userid) {
		this.userid = userid;
		this.setTitle(form.getTitle());
		this.setStatus(form.getStatus());
		this.setDate(CommonUtils.convertDate(form.getDate()));
		this.setStartTime(form.getStartTime());
		this.setEndTime(form.getEndTime());
		this.setPlace(form.getPlace());
		this.setDetail(form.getDetail());
		this.setPersonal(form.isPersonal());
		this.setTeam(form.isTeam());
	}

	public Schedule(ScheduleForm form) {
		this(form, form.getUserid());
		this.id = form.getId();
	}

	@Id
	public int id;
	public String userid;
	public String title;
	public String status;
	public LocalDate date;
	@Column(value="startTime")
	public String startTime;
	@Column(value="endTime")
	public String endTime;
	public String place;
	public String detail;

	@Column(value="isPersonal")
	public boolean isPersonal;
	@Column(value="isTeam")
	public boolean isTeam;
}

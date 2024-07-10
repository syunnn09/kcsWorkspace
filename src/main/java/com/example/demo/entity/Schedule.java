package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;

import com.example.demo.form.ScheduleForm;
import com.example.demo.utils.CommonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements Persistable<Integer> {

	public Schedule(ScheduleForm form, String userid) {
		this.userid = userid;
		this.setInt_id(form.getId());
		this.setTitle(form.getTitle());
		this.setStatus(form.getStatus());
		this.setDate(CommonUtils.convertDate(form.getDate()));
		this.setStartTime(form.getStartTime());
		this.setEndTime(form.getEndTime());
		this.setPlace(form.getPlace());
		this.setDetail(form.getDetail());
		this.setPersonal(form.isPersonal());
		this.setTeam(form.isTeam());
		this.isNew = false;
	}

	public Schedule(ScheduleForm form) {
		this(form, form.getUserid());
		this.int_id = form.getId();
	}

	@Id
	@Column("id")
	public int int_id;
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

	@Transient
	public boolean isNew = true;

	@Override
	public Integer getId() {
		return this.int_id;
	}
}

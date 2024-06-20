package com.example.demo.form;

import org.springframework.data.annotation.Id;

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
}

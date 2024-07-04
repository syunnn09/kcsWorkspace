package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.example.demo.consts.Const.TimecardTimeStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimecardTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userid;
	private LocalDateTime start;
	private LocalDateTime end;
	private int status;

	public TimecardTime(String userid, TimecardTimeStatus status) {
		this.userid = userid;
		this.start = LocalDateTime.now();
		this.setStatus(status);
	}

	public void setStatus(TimecardTimeStatus status) {
		this.status = status.get();
	}
}

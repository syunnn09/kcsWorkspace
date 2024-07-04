package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimecardTime {

	@Id
	private int id;
	private String userid;
	private LocalDateTime start;
	private LocalDateTime end;
	private int status;

	public void setStatus(TimecardTimeStatus status) {
		this.status = status.get();
	}
}

enum TimecardTimeStatus {
	WORK(1),
	REST(2);

	private int status;

	private TimecardTimeStatus(int status) {
		this.status = status;
	}

	public int get() {
		return this.status;
	}
}

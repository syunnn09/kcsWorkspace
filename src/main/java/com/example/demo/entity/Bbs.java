package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bbs {

	@Id
	private int id;
	private int thread_id;
	private String userid;
	private String text;
	
	private LocalDateTime post_at;

	@Transient
	private User user;

	@Transient
	private boolean isNew;

	public String postTime() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日(E) hh:mm:ss");
		return post_at.format(dateTimeFormatter);
	}

	public String username() {
		return this.user.getUsername();
	}

	public String userid() {
		return this.user.getUserid();
	}

	public boolean isNew() {
		return true;
	}
}

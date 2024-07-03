package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;

import com.example.demo.form.PhoneForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

	@Id
	private int id;
	private String to;
	private LocalDateTime time;
	private String from;
	private String phone;
	private String message;
	private String self;
	private boolean checked;

	public Phone(PhoneForm form) {
		to = form.getTo();
		time = convert(form.getTime());
		from = form.getFrom();
		phone = form.getPhone();
		message = form.getMessage();
		self = form.getSelf();
		checked = false;
	}

	private LocalDateTime convert(String date) {
		return LocalDateTime.parse(date);
	}

	public String datetime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM:dd HH:mm");
		return time.format(formatter);
	}

	public String status() {
		return checked ? "確認済" : "確認";
	}
}

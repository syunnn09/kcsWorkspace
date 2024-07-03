package com.example.demo.form;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneForm {

	private String to;
	private String time;
	private String from;
	private String phone;
	private String message;
	private int checked = 0;
	private String self;

	public String formattedTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return now.format(formatter);
	}
}

package com.example.demo.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkForm {

	private String userid;
	private int year;
	private int month;
	private int date;
	private String[] start;
	private String[] end;
	private String[] progress;
	private String[] remarks;
	private String notices;

	public LocalDate day() {
		return LocalDate.of(year, month, date);
	}

	public String dayStr() {
		LocalDate date = day();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(formatter);
	}
}

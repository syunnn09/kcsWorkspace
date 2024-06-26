package com.example.demo.form;

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
}

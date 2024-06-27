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
	private String[] detail;
	private String[] progress;
	private String[] remarks;
	private String notices;
	private int count;

	public LocalDate day() {
		return LocalDate.of(year, month, date);
	}

	public String getStart(int index) {
		if (index < start.length) {
			return start[index];
		}
		return "";
	}

	public String getEnd(int index) {
	    if (index < end.length) {
	        return end[index];
	    }
	    return "";
	}

	public String getDetail(int index) {
	    if (index < detail.length) {
	        return detail[index];
	    }
	    return "";
	}

	public String getProgress(int index) {
	    if (index < progress.length) {
	        return progress[index];
	    }
	    return "";
	}

	public String getRemarks(int index) {
	    if (index < remarks.length) {
	        return remarks[index];
	    }
	    return "";
	}


	public String dayStr() {
		LocalDate date = day();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(formatter);
	}

	public boolean isNew() {
		return true;
	}
}

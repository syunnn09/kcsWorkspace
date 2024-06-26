package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.example.demo.form.WorkDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Work {

	@Id
	private int id;
	private String userid;
	private String notices;
	private String day;
	private LocalDateTime updateDate;

	@Transient
	private List<WorkDetail> workDetails;

	public String upload() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return updateDate.format(dateTimeFormatter);
	}

	public String year() {
		return String.valueOf(updateDate.getYear());
	}

	public String month() {
		return String.valueOf(updateDate.getMonthValue());
	}

	public String date() {
		return String.valueOf(updateDate.getDayOfMonth());
	}
}

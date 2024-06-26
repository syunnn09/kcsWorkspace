package com.example.demo.entity;

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

	@Transient
	private List<WorkDetail> workDetails;
}

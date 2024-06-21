package com.example.demo.entity;

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

	@Transient
	private User user;
}

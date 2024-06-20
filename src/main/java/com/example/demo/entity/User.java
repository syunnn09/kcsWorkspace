package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	public String userid;
	public String username;
	public String roll;

	@Column(value="departmentId")
	public int departmentId;

	@Column(value="departmentName")
	public String departmentName;
}

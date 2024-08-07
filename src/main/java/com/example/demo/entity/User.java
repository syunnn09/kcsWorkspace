package com.example.demo.entity;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class User {

	@Id
	public String userid;
	public String username;
	public String roll;

	@Column(value="departmentId")
	public int departmentId;

	@Column(value="departmentName")
	public String departmentName;

	public String name() {
		return this.username;
	}
}

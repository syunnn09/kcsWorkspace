package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	@ManyToOne
	@JoinColumn(name="departmentId", referencedColumnName = "ID")
	public Department dept;

	@Column(value="departmentId")
	public int departmentId;

	@Column(value="departmentName")
	public String departmentName;

	public String name() {
		return this.username;
	}
}

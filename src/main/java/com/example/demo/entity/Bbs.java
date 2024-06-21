package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bbs {

	@Autowired
	UserRepository repo;

	@Id
	private int id;
	private int thread_id;
	private String userid;
	private String text;

	@Transient
	private User user;

	public User getUser() {
		if (this.user == null) {
			this.user = repo.findById(userid).get();
		}
		return this.user;
	}
}

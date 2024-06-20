package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;

	@Override
	public User login(String id, String pass) {
		return repo.login(id, pass); 
	}

}

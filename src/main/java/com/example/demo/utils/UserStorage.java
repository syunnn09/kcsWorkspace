package com.example.demo.utils;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {

	private static UserStorage instance;
	private Set<String> users;

	private UserStorage() {
		users = new HashSet<>();
	}

	public static UserStorage getInstance() {
		synchronized(UserStorage.class) {
			if (instance == null) {
				instance = new UserStorage();
			}
		}
		return instance;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUser(String userName) throws Exception {
		if (users.contains(userName)) {
			throw new Exception("User already exists with userName: " + userName);
		}
		users.add(userName);
	}

	public boolean isExistsUser(String user) {
		return getUsers().contains(user);
	}
}

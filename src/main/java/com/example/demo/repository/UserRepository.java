package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	@Query("SELECT userid, username, roll, departmentId, dep.name as departmentName FROM users INNER JOIN department as dep ON users.departmentId = dep.ID WHERE userid=:id AND password=:password")
	public User login(@Param("id") String id, @Param("password") String password);
}

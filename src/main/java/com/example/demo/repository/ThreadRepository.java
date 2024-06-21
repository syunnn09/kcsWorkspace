package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Thread;

public interface ThreadRepository extends CrudRepository<Thread, Integer> {

	@Modifying
	@Query("INSERT INTO thread(title) VALUES(:title)")
	public void insert(String title);
}

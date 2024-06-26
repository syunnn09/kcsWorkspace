package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Work;

public interface WorkRepository extends CrudRepository<Work, Integer> {

	@Query("SELECT * FROM work WHERE work.userid = :userid")
	public List<Work> getWorks(String userid);
}

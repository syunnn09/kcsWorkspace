package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.form.WorkDetail;

public interface WorkDetailRepository extends CrudRepository<WorkDetail, Integer> {

	@Query("SELECT * FROM work_detail WHERE workid = :workid")
	public List<WorkDetail> getWorkDetail(int workid);
}

package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Timecard;

public interface TimecardRepository extends CrudRepository<Timecard, String> {

}

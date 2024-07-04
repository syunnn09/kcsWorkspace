package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.TimecardTime;

public interface TimecardTimeRepository extends CrudRepository<TimecardTime, Integer> {

}

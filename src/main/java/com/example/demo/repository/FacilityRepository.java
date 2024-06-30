package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Facility;

public interface FacilityRepository extends CrudRepository<Facility, Integer> {

}

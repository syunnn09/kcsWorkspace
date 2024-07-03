package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Integer>{

	List<Phone> findByToIs(String to);

	List<Phone> findByToIsAndCheckedIs(String to, int check);
}

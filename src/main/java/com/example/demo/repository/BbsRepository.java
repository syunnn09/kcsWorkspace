package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.form.BbsForm;

public interface BbsRepository extends CrudRepository<BbsForm, Integer> {

}

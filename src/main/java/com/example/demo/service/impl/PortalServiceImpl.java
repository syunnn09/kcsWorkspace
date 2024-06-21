package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Thread;
import com.example.demo.repository.ThreadRepository;
import com.example.demo.service.PortalService;

@Service
public class PortalServiceImpl implements PortalService {

	@Autowired
	ThreadRepository threadRepository;

	@Override
	public List<Thread> getAllThreads() {
		Iterable<Thread> all = threadRepository.findAll();
		List<Thread> threads = new ArrayList<>();
		all.forEach(threads::add);
		return threads;
	}

	@Override
	public void saveThread(String title) {
		threadRepository.insert(title);
	}
}

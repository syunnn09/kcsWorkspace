package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bbs;
import com.example.demo.entity.Thread;
import com.example.demo.entity.User;
import com.example.demo.entity.Work;
import com.example.demo.form.BbsForm;
import com.example.demo.form.WorkDetail;
import com.example.demo.repository.BbsRepository;
import com.example.demo.repository.ThreadRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WorkDetailRepository;
import com.example.demo.repository.WorkRepository;
import com.example.demo.service.PortalService;

@Service
public class PortalServiceImpl implements PortalService {

	@Autowired
	ThreadRepository threadRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BbsRepository bbsRepository;

	@Autowired
	WorkRepository workRepository;

	@Autowired
	WorkDetailRepository workDetailRepository;

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

	@Override
	public List<Bbs> getBbs(int id) {
		return threadRepository.getBbs(id);
	}

	@Override
	public User getUser(Bbs bbs) {
		return userRepository.getUser(bbs.getUserid());
	}

	@Override
	public void saveBbs(BbsForm bbs) {
		bbsRepository.save(bbs);
	}

	@Override
	public int saveWorkReport(Work work) {
		workRepository.save(work);
		return work.getId();
	}

	@Override
	public void saveWorkDetails(List<WorkDetail> details) {
		workDetailRepository.saveAll(details);
	}

	@Override
	public List<Work> getWorks(String userid) {
		Iterable<Work> works = workRepository.getWorks(userid);
		List<Work> work = new ArrayList<>();
		works.forEach(work::add);
		return work;
	}

	@Override
	public Work findWork(int workId) {
		return workRepository.findById(workId).get();
	}
}

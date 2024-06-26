package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bbs;
import com.example.demo.entity.Facility;
import com.example.demo.entity.FacilityReserve;
import com.example.demo.entity.Thread;
import com.example.demo.entity.User;
import com.example.demo.entity.Work;
import com.example.demo.form.BbsForm;
import com.example.demo.form.WorkDetail;
import com.example.demo.repository.BbsRepository;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.repository.FacilityReserveRepository;
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

	@Autowired
	FacilityRepository facilityRepository;

	@Autowired
	FacilityReserveRepository facilityReserveRepository;

	public <T> List<T> convertToArray(Iterable<T> obj) {
		List<T> list = new ArrayList<>();
		obj.forEach(list::add);
		return list;
	}

	@Override
	public List<Thread> getAllThreads() {
		Iterable<Thread> threads = threadRepository.findAll();
		return convertToArray(threads);
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
		works.forEach(w -> {
			w.setUsername("今村");
			work.add(w);
		});
		return work;
	}

	@Override
	public Work findWork(int workId) {
		return workRepository.findById(workId).get();
	}

	@Override
	public List<WorkDetail> getWorkDetail(int workid) {
		return workDetailRepository.getWorkDetail(workid);
	}

	@Override
	public List<Facility> getFacilities() {
		Iterable<Facility> facilities = facilityRepository.findAll();
		return convertToArray(facilities);
	}

	@Override
	public List<FacilityReserve> getReserves() {
		Iterable<FacilityReserve> reserves = facilityReserveRepository.findAll();
		return convertToArray(reserves);
	}
}

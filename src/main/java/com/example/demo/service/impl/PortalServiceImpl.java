package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.consts.Const.TimecardStatus;
import com.example.demo.entity.Bbs;
import com.example.demo.entity.Facility;
import com.example.demo.entity.FacilityReserve;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Thread;
import com.example.demo.entity.Timecard;
import com.example.demo.entity.TimecardTime;
import com.example.demo.entity.User;
import com.example.demo.entity.Work;
import com.example.demo.form.BbsForm;
import com.example.demo.form.WorkDetail;
import com.example.demo.repository.BbsRepository;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.repository.FacilityReserveRepository;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.repository.ThreadRepository;
import com.example.demo.repository.TimecardRepository;
import com.example.demo.repository.TimecardTimeRepository;
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

	@Autowired
	PhoneRepository phoneRepository;

	@Autowired
	TimecardRepository timecardRepository;

	@Autowired
	TimecardTimeRepository timecardTimeRepository;

	public <T> List<T> convertToList(Iterable<T> obj) {
		List<T> list = new ArrayList<>();
		obj.forEach(list::add);
		return list;
	}

	public <T> T get(Optional<T> obj) {
		if (obj.isEmpty()) {
			return null;
		}
		return obj.get();
	}

	@Override
	public List<Thread> getAllThreads() {
		Iterable<Thread> threads = threadRepository.findAll();
		return convertToList(threads);
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
		return get(workRepository.findById(workId));
	}

	@Override
	public List<WorkDetail> getWorkDetail(int workid) {
		return workDetailRepository.getWorkDetail(workid);
	}

	@Override
	public List<Facility> getFacilities() {
		Iterable<Facility> facilities = facilityRepository.findAll();
		return convertToList(facilities);
	}

	@Override
	public List<FacilityReserve> getReserves() {
		Iterable<FacilityReserve> reserves = facilityReserveRepository.findAll();
		return convertToList(reserves);
	}

	@Override
	public List<Phone> getPhone(String userid) {
		return phoneRepository.findByToIs(userid);
	}

	@Override
	public Phone getPhoneById(int phoneId) {
		return get(phoneRepository.findById(phoneId));
	}

	@Override
	public boolean submitPhone(int phoneId) {
		Phone phone = getPhoneById(phoneId);
		return submitPhone(phone);
	}

	@Override
	public boolean submitPhone(Phone phone) {
		if (phone != null) {
			phone.setChecked(true);
			phoneRepository.save(phone);
			return true;
		}
		return false;
	}

	@Override
	public void savePhone(Phone phone) {
		phoneRepository.save(phone);
	}

	@Override
	public Timecard getTimecard(String userid) {
		Optional<Timecard> card  = timecardRepository.findById(userid);
		return get(card);
	}

	@Override
	public void saveTimecard(String userid) {
		Timecard card = new Timecard(userid, TimecardStatus.LEAVING.getStatus(), 0, 0, true);
		timecardRepository.save(card);
	}

	@Override
	public List<Timecard> getCards() {
		Iterable<Timecard> cards = timecardRepository.findAll();
		return convertToList(cards);
	}

	@Override
	public TimecardTime getTimecardTime(int id) {
		return get(timecardTimeRepository.findById(id));
	}
}

package com.example.demo.service;

import java.util.List;

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
import com.example.demo.form.ScheduleForm;
import com.example.demo.form.WorkDetail;

public interface PortalService {

	public List<Thread> getAllThreads();

	public void saveThread(String title);

	public List<Bbs> getBbs(int id);

	public User getUser(Bbs bbs);

	public void saveBbs(BbsForm bbs);

	public int saveWorkReport(Work work);

	public void saveWorkDetails(List<WorkDetail> details);

	public List<Work> getWorks(String userid);

	public Work findWork(int workId);

	public List<WorkDetail> getWorkDetail(int workid);

	public List<Facility> getFacilities();

	public List<FacilityReserve> getReserves();

	public List<Phone> getPhone(String userid);

	public Phone getPhoneById(int phoneId);

	public boolean submitPhone(int phoneId); 

	public boolean submitPhone(Phone phone);

	public void savePhone(Phone phone);

	public Timecard getTimecard(String userid);

	public void saveTimecard(String userid);

	public List<Timecard> getCards();

	public TimecardTime getTimecardTime(int id);

	public ScheduleForm getScheduleForm(int id);
}

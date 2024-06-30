package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Bbs;
import com.example.demo.entity.Facility;
import com.example.demo.entity.FacilityReserve;
import com.example.demo.entity.Thread;
import com.example.demo.entity.User;
import com.example.demo.entity.Work;
import com.example.demo.form.BbsForm;
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

	List<Facility> getFacilities();

	List<FacilityReserve> getReserves();
}

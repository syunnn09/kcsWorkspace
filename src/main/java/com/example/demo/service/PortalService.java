package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Bbs;
import com.example.demo.entity.Thread;
import com.example.demo.entity.User;
import com.example.demo.form.BbsForm;

public interface PortalService {

	public List<Thread> getAllThreads();

	public void saveThread(String title);

	public List<Bbs> getBbs(int id);

	public User getUser(Bbs bbs);

	public void saveBbs(BbsForm bbs);
}

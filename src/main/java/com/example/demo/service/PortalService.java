package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Bbs;
import com.example.demo.entity.Thread;
import com.example.demo.entity.User;

public interface PortalService {

	public List<Thread> getAllThreads();

	public void saveThread(String title);

	public List<Bbs> getBbs(String title);

	public User getUser(Bbs bbs);
}

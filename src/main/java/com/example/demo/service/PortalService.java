package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Thread;

public interface PortalService {

	public List<Thread> getAllThreads();

	public void saveThread(String title);
}

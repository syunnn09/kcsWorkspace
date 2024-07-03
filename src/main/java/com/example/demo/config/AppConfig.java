package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.service.impl.PortalServiceImpl;
import com.example.demo.service.impl.ScheduleServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;

@Configuration
public class AppConfig {

	@Bean(name = "userService")
	UserServiceImpl userService() {
		return new UserServiceImpl();
	}

	@Bean(name = "scheduleService")
	ScheduleServiceImpl scheduleService() {
		return new ScheduleServiceImpl();
	}

	@Bean(name = "portalService")
	PortalServiceImpl portalService() {
		return new PortalServiceImpl();
	}
}

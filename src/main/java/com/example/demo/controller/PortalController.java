package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Schedules;
import com.example.demo.entity.User;
import com.example.demo.form.ScheduleForm;
import com.example.demo.service.NotificationService;
import com.example.demo.service.ScheduleService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/portal")
public class PortalController {

	@Autowired
	NotificationService service;

	@Autowired
	ScheduleService scheduleService;

	private String redirectLogin() {
		return "redirect:/login";
	}

	@GetMapping(value={"", "/"})
	public String portal(@PathParam("ad") String ad, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return redirectLogin();
		}
		if (ad == null) {
			ad = "0";
		}
		int next = Integer.valueOf(ad);
		model.addAttribute("user", user);
		model.addAttribute("notifications", service.getNotification(user.getUserid()));
		Schedules personalSchedules = new Schedules(scheduleService.getPersonalSchedule(user.getUserid(), next), next);
		model.addAttribute("personal", personalSchedules.getSchedules());
		Schedules teamSchedules = new Schedules(scheduleService.getTeamSchedule(user.getDepartmentId(), next), next);
		model.addAttribute("team", teamSchedules.getSchedules());

		model.addAttribute("prevAd", next+1);
		model.addAttribute("nextAd", next-1);

		return "portal";
	}

	@GetMapping("regist")
	public String regist(
		@PathParam("day") String day,
		@PathParam("status") String status,
		@ModelAttribute ScheduleForm schedule,
		HttpSession session
	) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return redirectLogin();
		}
		schedule.setDate(day);
		if (status != null) {
			if (status.equals("personal")) {
				schedule.setPersonal(true);
			} else if (status.equals("team")) {
				schedule.setTeam(true);
			}
		}
		return "regist";
	}

	@PostMapping("regist")
	public String doRegist(ScheduleForm schedule, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return redirectLogin();
		}
		scheduleService.save(schedule, user.getUserid());
		return "redirect:/portal";
	}

	List<String> texts = new ArrayList<>();

	@GetMapping("bbs")
	public String bbs(Model model) {
		model.addAttribute("bbs", texts);
		return "bbs";
	}

	@PostMapping("bbs")
	public String doBbs(@PathParam("text") String text) {
		this.texts.add(text);
		return "redirect:bbs";
	}
}

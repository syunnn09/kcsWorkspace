package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.consts.Const.Report;
import com.example.demo.entity.Bbs;
import com.example.demo.entity.Schedules;
import com.example.demo.entity.Thread;
import com.example.demo.entity.User;
import com.example.demo.entity.Work;
import com.example.demo.form.BbsForm;
import com.example.demo.form.ScheduleForm;
import com.example.demo.form.WorkDetail;
import com.example.demo.form.WorkForm;
import com.example.demo.service.NotificationService;
import com.example.demo.service.PortalService;
import com.example.demo.service.ScheduleService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/portal")
public class PortalController {

	@Autowired
	UserService userService;

	@Autowired
	NotificationService service;

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	PortalService portalService;

	@Autowired
	HttpSession session;

	private String redirectLogin() {
		return "redirect:/login";
	}

	private User getUser() {
		return (User) session.getAttribute("user");
	}

	@GetMapping(value={"", "/"})
	public String portal(@PathParam("ad") String ad, Model model) {
		User user = getUser();
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
		@ModelAttribute ScheduleForm schedule
	) {
		User user = getUser();
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
	public String doRegist(ScheduleForm schedule) {
		User user = getUser();
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
		List<Thread> threads = portalService.getAllThreads();
		model.addAttribute("threads", threads);
		return "bbs";
	}

	@GetMapping("bbs/thread")
	public String bbsDetail(@ModelAttribute BbsForm form, @PathParam("id") int id, Model model) {
		User user = getUser();
		if (user == null) {
			return redirectLogin();
		}

		List<Bbs> bbs = portalService.getBbs(id);
		if (bbs.size() == 0) {
			return "redirect:/portal/bbs";
		}
		bbs.forEach(b -> b.setUser(portalService.getUser(b)));
		String title = "プロ野球";
		form.setThread_id(id);
		model.addAttribute("bbs", bbs);
		model.addAttribute("title", title);
		return "thread_detail";
	}

	@PostMapping("bbs/thread")
	public String writeBbs(
		@ModelAttribute BbsForm bbs,
		@PathParam("thread_id") int id,
		@PathParam("text") String text,
		HttpSession session
	) {
		User user = getUser();
		if (user == null) {
			return redirectLogin();
		}

		bbs.setUserid(user.getUserid());
		portalService.saveBbs(bbs);
		return "redirect:/portal/bbs/thread?id=" + id;
	}

	@GetMapping("create_thread")
	public String createThread() {
		return "create_thread";
	}

	@PostMapping("create_thread")
	public String create(@PathParam("title") String title) {
		portalService.saveThread(title);
		return "redirect:bbs";
	}

	@GetMapping("report")
	public String report(Model model) {
		model.addAttribute("reports", Report.values());
		model.addAttribute("works", portalService.getWorks(getUser().getUserid()));
		System.out.println(model.getAttribute("works"));
		return "report/index";
	}

	@GetMapping("report/{type}")
	public String showReport(@ModelAttribute WorkForm form, @PathVariable String type, Model model) {
		User user = getUser();
		if (user == null) {
			return redirectLogin();
		}
		if (!Report.contains(type)) {
			return "redirect:/portal/report";
		}
		LocalDate today = LocalDate.now();
		model.addAttribute("count", 3);
		model.addAttribute("today", today);
		model.addAttribute("user", user);
		form.setYear(today.getYear());
		form.setMonth(today.getMonthValue());
		form.setDate(today.getDayOfMonth());
		return "report/" + type;
	}

	@PostMapping("report/{type}")
	public String saveReport(@PathVariable String type, WorkForm form) {
		System.out.println(form);

		Work work = new Work();
		work.setDay(form.dayStr());
		work.setUserid(form.getUserid());
		work.setNotices(form.getNotices());
		int id = portalService.saveWorkReport(work);

		List<WorkDetail> works = new ArrayList<>();
		int count = form.getStart().length;
		for (int i = 0; i < count; i++) {
			works.add(new WorkDetail(form, i, id));
		}
		portalService.saveWorkDetails(works);

		return "redirect:/portal/report";
	}
}

package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Bbs;
import com.example.demo.entity.Calendar;
import com.example.demo.entity.Schedules;
import com.example.demo.entity.Thread;
import com.example.demo.entity.User;
import com.example.demo.form.BbsForm;
import com.example.demo.form.ScheduleForm;
import com.example.demo.service.PortalService;
import com.example.demo.service.ScheduleService;
import com.example.demo.service.UserService;
import com.example.demo.utils.NotificationManager;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/portal")
public class PortalController {

	@Autowired
	UserService userService;

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	PortalService portalService;

	@Autowired
	NotificationManager notificationManager;

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
		model.addAttribute("notifications", notificationManager.getNotifications());
		Schedules personalSchedules = new Schedules(scheduleService.getPersonalSchedule(user.getUserid(), next), next);
		model.addAttribute("personal", personalSchedules.getSchedules());
		Schedules teamSchedules = new Schedules(scheduleService.getTeamSchedule(user.getDepartmentId(), next), next);
		model.addAttribute("team", teamSchedules.getSchedules());

		model.addAttribute("prevAd", next+1);
		model.addAttribute("nextAd", next-1);
		model.addAttribute("showThisWeek", next != 0);

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
			schedule.setPersonal(status.equals("personal"));
			schedule.setTeam(status.equals("team"));
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

	@GetMapping("facility")
    public String showCalendar(Model model) {
		Calendar calendar = new Calendar();
		model.addAttribute("calendar", calendar);
		return "calendar";
    }

	@GetMapping("facility/regist")
	public String registFacility(@PathParam("date") String date, Model model) {
		LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d"));
		date = d.format(DateTimeFormatter.ofPattern("yyyy年M月d日"));
		model.addAttribute("facilities", portalService.getFacilities());
		model.addAttribute("date", date);
		return "regist_facility";
	}
}

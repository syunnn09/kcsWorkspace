package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.example.demo.entity.User;
import com.example.demo.entity.Work;
import com.example.demo.form.WorkDetail;
import com.example.demo.form.WorkForm;
import com.example.demo.service.PortalService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("portal/report")
public class ReportController {

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
	public String report(Model model) {
		User user = getUser();
		if (user == null) {
			return redirectLogin();
		}
		model.addAttribute("reports", Report.values());
		model.addAttribute("works", portalService.getWorks(getUser().getUserid()));
		return "report/index";
	}

	@GetMapping("/{type}")
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

	@GetMapping("/{type}/{id}")
	public String show(@PathVariable String id, Model model) {
		int workId = Integer.parseInt(id);
		Work work = portalService.findWork(workId);
		model.addAttribute("work", work);
		return "report/work";
	}

	@PostMapping("/{type}")
	public String saveReport(@PathVariable String type, WorkForm form) {
		System.out.println(form);

		Work work = new Work();
		work.setDay(form.dayStr());
		work.setUserid(form.getUserid());
		work.setNotices(form.getNotices());
		work.setUpdateDate(LocalDateTime.now());
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

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.consts.Const.TimecardStatus;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Timecard;
import com.example.demo.entity.User;
import com.example.demo.repository.TimecardRepository;
import com.example.demo.service.PortalService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class ApiController {

	@Autowired
	HttpSession session;

	@Autowired
	PortalService portalService;

	@Autowired
	TimecardRepository timecardRepository;

	private User getUser() {
		return (User) session.getAttribute("user");
	}

	private enum Status {
		SUCCESS("success"),
		FAILED("failed");

		private String status;
		Status(String status) {
			this.status = status;
		}
		public static Status getStatus(boolean bool) {
			return bool ? SUCCESS : FAILED;
		}
	}

	@GetMapping("")
	public String hello() {
		return "Hello Kcs Workspace!!";
	}

	@PostMapping("phone/submit")
	public String submitPhone(@RequestParam("phoneId") int phoneId) {
		if (getUser() == null) {
			return Status.FAILED.status;
		}

		Phone phone = portalService.getPhoneById(phoneId);
		if (!phone.getTo().equals(getUser().getUserid())) {
			return Status.FAILED.status;
		}
		boolean res = portalService.submitPhone(phone);
		Status status = Status.getStatus(res);
		return status.status;
	}

	@PostMapping("timecard/attendance")
	public String attendance() {
		if (getUser() == null) {
			return Status.FAILED.status;
		}

		Timecard card = portalService.getTimecard(getUser().getUserid());
		card.setStatus(TimecardStatus.ATWORK.getStatus());
		timecardRepository.save(card);
		return card.getStatus();
	}

	@PostMapping("timecard/leaving")
	public String leaving() {
		if (getUser() == null) {
			return Status.FAILED.status;
		}

		Timecard card = portalService.getTimecard(getUser().getUserid());
		card.setStatus(TimecardStatus.LEAVING.getStatus());
		timecardRepository.save(card);
		return card.getStatus();
	}

	@PostMapping("timecard/rest")
	public String rest() {
		if (getUser() == null) {
			return Status.FAILED.status;
		}

		Timecard card = portalService.getTimecard(getUser().getUserid());
		card.setStatus(TimecardStatus.REST.getStatus());
		timecardRepository.save(card);
		return card.getStatus();
	}

	@PostMapping("timecard/restEnd")
	public String restEnd() {
		if (getUser() == null) {
			return Status.FAILED.status;
		}

		Timecard card = portalService.getTimecard(getUser().getUserid());
		card.setStatus(TimecardStatus.ATWORK.getStatus());
		timecardRepository.save(card);
		return card.getStatus();
	}

	@PostMapping("timecard/reload")
	public String reload() {
		if (getUser() == null) {
			return Status.FAILED.status;
		}

		List<Timecard> cards = portalService.getCards();
		String content = "<div id=\"emp\">";
		for (Timecard card : cards) {
			content += "<p>" + card.getUserid() + " : " + card.getStatusString() + "</p>";
		}
		content += "</div>";
		return content;
	}
}

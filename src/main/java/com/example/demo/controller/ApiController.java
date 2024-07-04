package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.consts.Const.TimecardStatus;
import com.example.demo.consts.Const.TimecardTimeStatus;
import com.example.demo.entity.Phone;
import com.example.demo.entity.Timecard;
import com.example.demo.entity.TimecardTime;
import com.example.demo.entity.User;
import com.example.demo.repository.TimecardRepository;
import com.example.demo.repository.TimecardTimeRepository;
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

	@Autowired
	TimecardTimeRepository timecardTimeRepository;

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
		User user = getUser();
		if (user == null) {
			return Status.FAILED.status;
		}
		TimecardTime time = new TimecardTime(user.getUserid(), TimecardTimeStatus.WORK);
		timecardTimeRepository.save(time);

		Timecard card = portalService.getTimecard(getUser().getUserid());
		card.setStatus(TimecardStatus.ATWORK.getStatus());
		card.setTimeId(time.getId());
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

		TimecardTime time = portalService.getTimecardTime(card.getTimeId());
		time.setEnd(LocalDateTime.now());
		timecardTimeRepository.save(time);

		card.setTimeId(0);
		timecardRepository.save(card);
		return card.getStatus();
	}

	@PostMapping("timecard/rest")
	public String rest() {
		User user = getUser();
		if (user == null) {
			return Status.FAILED.status;
		}

		TimecardTime time = new TimecardTime(user.getUserid(), TimecardTimeStatus.REST);
		timecardTimeRepository.save(time);

		Timecard card = portalService.getTimecard(getUser().getUserid());
		card.setStatus(TimecardStatus.REST.getStatus());
		card.setRestId(time.getId());
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

		TimecardTime time = portalService.getTimecardTime(card.getRestId());
		time.setEnd(LocalDateTime.now());
		timecardTimeRepository.save(time);

		card.setRestId(0);
		timecardRepository.save(card);
		return card.getStatus();
	}

	@PostMapping("timecard/reload")
	public String reload() {
		if (getUser() == null) {
			return Status.FAILED.status;
		}

		List<Timecard> cards = portalService.getCards();
		String content = "";
		for (Timecard card : cards) {
			content += "<div class=\"empcard\"><p class=\"name\">" + card.getUserid() + "</p><p class=\"status\">" + card.getStatusString() + "</p></div>";
		}
		return content;
	}
}

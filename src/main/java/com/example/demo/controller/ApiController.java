package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Phone;
import com.example.demo.entity.User;
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
}

package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Phone;
import com.example.demo.entity.User;
import com.example.demo.form.PhoneForm;
import com.example.demo.service.PortalService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("portal/phone")
public class PhoneController {

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

	private LocalDateTime now() {
		return LocalDateTime.now();
	}

	@GetMapping(value={"", "/"})
	public String phone(Model model) {
		User user = getUser();
		if (user == null) {
			return redirectLogin();
		}
		List<Phone> phones = portalService.getPhone(user.getUserid());
		model.addAttribute("phones", phones);
		return "phone";
	}

	@GetMapping("/create")
	public String createPhone(@ModelAttribute PhoneForm form, Model model) {
		User user = getUser();
		if (user == null) {
			return redirectLogin();
		}
		form.setTime(now().toString());
		form.setSelf(user.getUserid());
		model.addAttribute("user", user);
		return "createPhone";
	}

	@PostMapping("/create")
	public String savePhone(PhoneForm form) {
		Phone phone = new Phone(form);
		portalService.savePhone(phone);
		return "redirect:/portal/phone";
	}
}

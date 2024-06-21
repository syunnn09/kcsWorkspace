package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	UserService service;

	private boolean isLogined(HttpSession session) {
		return session.getAttribute("user") != null;
	}

	@GetMapping("")
	public String index(HttpSession session) {
		if (isLogined(session)) {
			return "redirect:/portal";
		}
		return "redirect:login";
	}

	@GetMapping(value={"login", "/login"})
	public String login(HttpSession session) {
		if (isLogined(session)) {
			return "redirect:/portal";
		}
		return "login";
	}

	@PostMapping("login")
	public String doLogin(UserForm form, HttpSession session) {
		User user = service.login(form.getId(), form.getPassword());
		System.out.println(user);
		if (user == null) {
			return "redirect:/login";
		}
		session.setAttribute("user", user);
		return "redirect:/portal";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "logout";
	}
}

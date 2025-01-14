package com.example.hamo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin/dashboard")
	public String dashboard() {
		return "admin/dashboard";
	}
	
	@GetMapping("/admin/users")
	public String users() {
		return "admin/users";
	}
	
	@GetMapping("/admin/boards")
	public String boards() {
		return "admin/boards";
	}
	
	@GetMapping("/admin/reports")
	public String reports() {
		return "admin/reports";
	}
	
	@GetMapping("/admin/notice")
	public String notice() {
		return "admin/notice";
	}
	
	@GetMapping("/admin/noticeWrite")
	public String noticeWrite() {
		return "admin/noticeWrite";
	}

}

package com.example.hamo.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	@GetMapping("/member/login")
	public String login() {
		
		return "login/login";
	}
	
	// Home으로 가는 모든 버튼
	@GetMapping("/home")
	public String home() {
		
		return "index";
	}
}

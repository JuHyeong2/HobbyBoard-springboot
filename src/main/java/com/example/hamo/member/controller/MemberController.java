package com.example.hamo.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("/member/myPage")
	public String myPage() {
		return "user-inform/myPage";
	}
	
	@PostMapping("/member/checkPwd")
	public String checkPwd(){
	   return "user-inform/checkPwd";
	}
	@GetMapping("/member/myactivite")
	public String myActivite() {
		return "user-inform/myactivite";
	}
	@GetMapping("/member/mypost")
	public String myPost() {
		return "user-inform/mypost";
	}
	@GetMapping("/member/editMyPage")
	public String editMyPage() {
		return "user-inform/editMyPage";
	}
}

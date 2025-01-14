package com.example.hamo.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
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
	
	// 회원가입 페이지로 이동
	@GetMapping("/member/signUp")
	public String signUp() {
		return "login/signUp";
	}
	
	@PostMapping("/member/signUp")
	public String signUp(@ModelAttribute("Member") Member member ) {
		return "";
	}
	
	// 아이디 찾기 페이지로 이동
	@GetMapping("/member/findId")
	public String findId(Model model, HttpServletRequest request) {
		model.addAttribute("loc", request.getRequestURI());
		System.out.println(model.getAttribute("loc").toString());
		return "login/findId";
	}
	
	// 아이디 찾기 페이지로 이동
	@GetMapping("/member/findPwd")
	public String findPwd(Model model, HttpServletRequest request) {
		model.addAttribute("loc", request.getRequestURI());
		System.out.println(model.getAttribute("loc").toString());
		return "login/findPwd";
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

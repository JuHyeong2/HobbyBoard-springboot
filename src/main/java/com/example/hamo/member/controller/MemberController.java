package com.example.hamo.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.hamo.common.util.SmsCertificationUtil;
import com.example.hamo.member.model.service.MemberService;
import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("loginUser")
public class MemberController {
	
	private final MemberService mService;
	private final BCryptPasswordEncoder bcrypt;
	private final SmsCertificationUtil smsUtil;
	
	// 로그인 화면으로 가는 메서드
	@GetMapping("/member/login")
	public String loginView() {
		
		return "member/login";
	}
	
	// 로그인 기능을 하는 메소드
	@PostMapping("/member/login")
	@ResponseBody
	public String login(@ModelAttribute("Member") Member m, Model model) {
		Member loginUser = mService.login(m);
		if(loginUser != null && bcrypt.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
			model.addAttribute("loginUser", loginUser);
			return "success";
		}else {
			return "fail";
		}
	}
	
	@GetMapping("/member/logout")
	public String logOut(SessionStatus session) {
		session.setComplete();
		
		return "redirect:/home";
	}
	
	
	// Home으로 가는 모든 버튼
	@GetMapping("/home")
	public String home() {
		
		return "index";
	}
	
	// 회원가입 페이지로 이동
	@GetMapping("/member/signUp")
	public String signUp() {
		return "member/signUp";
	}
	
	@PostMapping("/member/signUp")
	public String signUp(@ModelAttribute("Member") Member member ) {
		
		member.setMemberPwd(bcrypt.encode(member.getMemberPwd()));
		int result = mService.insertMember(member);
		
		return "member/login";
	}
	
	// 회원가입 -> 휴대폰 인증번호 전송시 호출되는 메서드
	@PostMapping("/member/sendSMS")
	@ResponseBody
	public String sendSms(@RequestParam("phone") String phone, HttpServletResponse response) {
		System.out.println(phone);
		String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000); // 6자리 인증 코드를 랜덤으로 생성
		smsUtil.sendSMS(phone, certificationCode);
		
		return certificationCode;
	}
	
	@PostMapping("/member/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("userId") String userId) {
//		System.out.println(userId);
		int result = mService.idCheck(userId);
		System.out.println(result);
		return result;
	}
	
	// 아이디 찾기 페이지로 이동
	@GetMapping("/member/findId")
	public String findId(Model model, HttpServletRequest request) {
		model.addAttribute("loc", request.getRequestURI());
		System.out.println(model.getAttribute("loc").toString());
		return "member/findId";
	}
	
	// 아이디 찾기 페이지로 이동
	@GetMapping("/member/findPwd")
	public String findPwd(Model model, HttpServletRequest request) {
		model.addAttribute("loc", request.getRequestURI());
		System.out.println(model.getAttribute("loc").toString());
		return "member/findPwd";
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

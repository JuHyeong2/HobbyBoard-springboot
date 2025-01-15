package com.example.hamo.admin.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hamo.admin.model.service.AdminService;
import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
	private final AdminService aService;
	
	// dashboard mapping
	@GetMapping("dashboard")
	public String dashboard() {
		return "admin/dashboard";
	}
	
	// users mapping
	@GetMapping("users")
	public String users() {
		return "admin/users";
	}
	
	// boards mapping
	@GetMapping("boards")
	public String boards() {
		return "admin/boards";
	}
	
	// reports mapping
	@GetMapping("reports")
	public String reports() {
		return "admin/reports";
	}
	
	// notice mapping
	@GetMapping("notice")
	public String notice(Model model) {
		ArrayList<Admin> list = aService.selectNoticeList();
		model.addAttribute("list", list);
		return "admin/notice";
	}
	
	
	// noticeWrite mapping
	@GetMapping("noticeWrite")
	public String noticeWrite() {
		return "admin/noticeWrite";
	}
	
	// 공지사항 작성 
	@PostMapping("write")
	public String write(@ModelAttribute Admin admin, HttpSession session) {
		Member m = (Member)session.getAttribute("loginUser");
		// System.out.println(admin);
		admin.setWriter(1);
		
		int result = aService.writeNotice(admin);
		if(result > 0) {
			
		} 
//		else {
//			throw new AdminException("공지사항 등록 실패");
//		}
		return "redirect:/admin/notice";
	}

}

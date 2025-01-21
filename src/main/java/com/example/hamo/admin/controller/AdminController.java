package com.example.hamo.admin.controller;

import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.HashMap;
import java.util.Map;
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD

import com.example.hamo.admin.model.service.AdminService;
import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.member.model.vo.Member;

=======
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.hamo.admin.model.service.AdminService;
import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.common.Pagination;
import com.example.hamo.common.vo.PageInfo;
import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
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
	
<<<<<<< HEAD
	// users mapping
	@GetMapping("users")
	public String users() {
=======
	// 
	@GetMapping("users")
	public String users(Model model) {
		ArrayList<Member> mList = aService.selectAllMember();
		model.addAttribute("mList", mList);
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
		return "admin/users";
	}
	
	// boards mapping
	@GetMapping("boards")
<<<<<<< HEAD
	public String boards() {
=======
	public String boards(Model model) {
		ArrayList<Board> bList = aService.selectAllBoardList();
		model.addAttribute("bList", bList);
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
		return "admin/boards";
	}
	
	// reports mapping
	@GetMapping("reports")
	public String reports() {
		return "admin/reports";
	}
	
	// notice mapping
	@GetMapping("notice")
<<<<<<< HEAD
	public String notice(Model model) {
		ArrayList<Admin> list = aService.selectNoticeList();
		model.addAttribute("list", list);
=======
	public String notice(Model model, @RequestParam(value="page", defaultValue="1")int currentPage, HttpServletRequest request) {
		int listCount =  aService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 6);
		ArrayList<Admin> list = aService.selectNoticeList(pi);
		model.addAttribute("list", list).addAttribute("pi", pi).addAttribute("loc", request.getRequestURI());
//		System.out.println(list);
		
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
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
<<<<<<< HEAD
		// System.out.println(admin);
		admin.setWriter(1);
=======
//		 System.out.println(m);
		admin.setWriter(m.getMemberNo());
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
		
		int result = aService.writeNotice(admin);
		if(result > 0) {
			
		} 
//		else {
//			throw new AdminException("공지사항 등록 실패");
//		}
		return "redirect:/admin/notice";
	}
<<<<<<< HEAD

}
=======
	
	// 공지사항 수정 페이지 이동 
	@PostMapping("edit")
	public String edit(@RequestParam("id") int id, @RequestParam("page") String page, HttpSession session, Model model ) {
		Admin admin = aService.selectNoticeOne(id);
		model.addAttribute("admin", admin).addAttribute("page", page);
		return "admin/noticeEdit";
	}
	
	// 공지사항 수정
	@PostMapping("update")
	public String update(@ModelAttribute Admin admin, @RequestParam("page") String page) {
		/* System.out.println(admin); */
		int result = aService.updateNotice(admin);
//		if(result > 0) {
//			
//		}else {
//			
//		}
		return "redirect:/admin/notice";
	}
	
	// 공지사항 삭제
	@PostMapping("delete")
	public String deleteNotice(@RequestParam("id")int noticeId) {
		int result = aService.deleteNotice(noticeId);
		return "redirect:/admin/notice";
	}
	
	@PostMapping("changeStatus")
	@ResponseBody
	public Map<String, Object> changeStatus(@RequestParam("mId") String mId, @RequestParam("isStatus") String isStatus) {
	    HashMap<String, String> map = new HashMap<>();
	    if (isStatus.equals("true")) {
	        isStatus = "Y";
	    } else {
	        isStatus = "N";
	    }
	    
	    map.put("mId", mId);
	    map.put("isStatus", isStatus);

	    // 상태 변경 처리
	    int result = aService.changeStatus(map);

	    // 상태 값을 서버에서 반환
	    Map<String, Object> response = new HashMap<>();
	    response.put("result", result);
	    response.put("status", isStatus.equals("Y") ? "Y" : "N");  // 상태 텍스트 반환

	    return response;
	}
	
	@GetMapping("searchUser")
	@ResponseBody
	public ArrayList<Member> searchUser(@RequestParam("searchValue") String searchValue) {
		ArrayList<Member> searchList = new ArrayList<Member>();
		
		if(!searchValue.trim().equals("")) {
		searchList = aService.searchUserList(searchValue);
		System.out.println(searchList);
		} else {
		searchList = aService.selectAllMember();
		}
		return searchList;
	}
}
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba

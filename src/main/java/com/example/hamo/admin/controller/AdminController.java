package com.example.hamo.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.hamo.admin.model.service.AdminService;
import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.admin.model.vo.Dashboard;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.common.Pagination;
import com.example.hamo.common.vo.PageInfo;
import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
	private final AdminService aService;

	// dashboard mapping
	@GetMapping("dashboard")
	public String dashboard(Model model, HttpServletRequest request) {
		ArrayList<Dashboard> boardCount = aService.boardCount();
		ArrayList<Dashboard> userCount = aService.userCount();
		ArrayList<Dashboard> dailyUserCount = aService.dailyUserCount();
		System.out.println(dailyUserCount);
//		System.out.println(userCount);
//		System.out.println(boardCount);
		model.addAttribute("boardCount", boardCount).addAttribute("userCount", userCount).addAttribute("dailyUserCount",dailyUserCount);
		return "admin/dashboard";
	}
	
	
	
	
	
	@GetMapping("home")
	public String home() {
		return "redirect:/";
	}

	//
	@GetMapping("users")
	public String users(Model model) {
		ArrayList<Member> mList = aService.selectAllMember();
		model.addAttribute("mList", mList);
		return "admin/users";
	}

	// boards mapping
	@GetMapping("boards")
	public String boards(Model model) {
		ArrayList<Board> bList = aService.selectAllBoardList();
		model.addAttribute("bList", bList);
		return "admin/boards";
	}



	// notice mapping
	@GetMapping("notice")
	public String notice(Model model, @RequestParam(value = "page", defaultValue = "1") int currentPage,
			HttpServletRequest request) {
		int listCount = aService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 6);
		ArrayList<Admin> list = aService.selectNoticeList(pi);
		model.addAttribute("list", list).addAttribute("pi", pi).addAttribute("loc", request.getRequestURI());
//		System.out.println(list);

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

		Member m = (Member) session.getAttribute("loginUser");
//		 System.out.println(m);
		admin.setContent(admin.getContent());

		admin.setWriter(m.getMemberNo());

		int result = aService.writeNotice(admin);
		if (result > 0) {

		}
//		else {
//			throw new AdminException("공지사항 등록 실패");
//		}
		return "redirect:/admin/notice";
	}

	// 공지사항 수정 페이지 이동
	@PostMapping("edit")
	public String edit(@RequestParam("id") int id, @RequestParam("page") String page, HttpSession session,
			Model model) {
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
	public String deleteNotice(@RequestParam("id") int noticeId) {
		int result = aService.deleteNotice(noticeId);
		return "redirect:/admin/notice";
	}

	@PostMapping("changeStatus")
	@ResponseBody
	public Map<String, Object> changeStatus(@RequestParam("mId") String mId,
			@RequestParam("isStatus") String isStatus) {
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
		response.put("status", isStatus.equals("Y") ? "Y" : "N"); // 상태 텍스트 반환

		return response;
	}

	@GetMapping("searchUser")
	@ResponseBody
	public ArrayList<Member> searchUser(@RequestParam("searchValue") String searchValue) {
		ArrayList<Member> searchList = new ArrayList<Member>();

		if (!searchValue.trim().equals("")) {
			searchList = aService.searchUserList(searchValue);
			System.out.println(searchList);
		} else {
			searchList = aService.selectAllMember();
		}
		return searchList;
	}

	@GetMapping("deleteBoard")
	public String deleteBoard(@RequestParam("boardNo") int boardNo) {
		int result = aService.deleteBoard(boardNo);
		if (result > 0) {
			return "redirect:/admin/boards";
		} else {
			return "";
		}
	}

	@PostMapping("searchBoard")
	@ResponseBody
	public ArrayList<Board> searchBoard(@RequestParam("searchCategory") String searchCategory,
			@RequestParam("searchValue") String searchValue) {
//		System.out.println("카테고리 : " + searchCategory);
//		System.out.println("검색어 : " + searchValue);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchCategory", searchCategory);
		map.put("searchValue", searchValue);
		ArrayList<Board> searchList = aService.searchBoardList(map);
//		System.out.println(searchList);

		return searchList;
	}

}

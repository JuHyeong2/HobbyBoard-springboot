package com.example.hamo.board.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hamo.admin.model.service.AdminService;
import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.service.BoardService;
import com.example.hamo.board.model.vo.Board;
<<<<<<< HEAD
import com.example.hamo.member.model.vo.Member;

=======
import com.example.hamo.common.Pagination;
import com.example.hamo.common.vo.PageInfo;
import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
>>>>>>> 1d8a7baad3fa21d37c3ff3e345117920d381b651
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


	

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService bService;
<<<<<<< HEAD
	
	@GetMapping("/{id}")
	public String boarDetails(@PathVariable("id") int bNo,Model model) {
=======
	private final AdminService aService;
	@GetMapping("boardDetails")
	public String boarDetails(@RequestParam("boardNo") int boardNo,Model model) {
>>>>>>> 1d8a7baad3fa21d37c3ff3e345117920d381b651
		
		System.out.println("boardNo : " + bNo); //테스트
		
		Board board = bService.selectBoard(bNo);
		System.out.println(board); 					//테스트
		model.addAttribute("board", board);
		
		return "board/boardDetails";
	}
	
	// 게시글 쓰기 뷰로 이동하는 메소드
	@GetMapping("boardInsert")
	public String boardInsertView() {
		return "board/boardInsert"; 
	}
	
	@PostMapping("boardInsert")
	public String boardInsert(@ModelAttribute("Board") Board b, HttpSession session) {
		Member m = (Member)session.getAttribute("loginUser");
		b.setMemberNo(m.getMemberNo());
		int result = bService.boardInsert(b);
		
		return "redirect:/" ;
	}
	
	@GetMapping("boardUpdate")
	public String boardUpdate() {
		return "board/boardUpdate";
	}
		
	
	@GetMapping("noticeView")
	public String noticeView(Model model, @RequestParam(value="page", defaultValue="1")int currentPage,HttpServletRequest request) {
		int listCount =  aService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 6);
		ArrayList<Admin> list = aService.selectNoticeList(pi);
		model.addAttribute("aList", list).addAttribute("pi", pi).addAttribute("loc", request.getRequestURI());
		
		System.out.println(list);
		
		return "board/noticeView";
	}
	
	@GetMapping("/{id}/{page}")
	public String selectNotice(@PathVariable("id") int bId, @PathVariable("page") int page, HttpSession session, Model model) {
		Member m = (Member)session.getAttribute("loginUser");
		Admin a = bService.selectNotice(m, bId);
		
		if(a != null) {
			model.addAttribute("a", a).addAttribute("page", page);
			return "board/noticeDetail";
		} else {
			return null;
		}
	}
	
	
	
	@GetMapping("project")
	public String project() {
		return "board/project";
	}
	
	

}

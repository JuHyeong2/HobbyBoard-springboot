package com.example.hamo.board.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hamo.board.model.service.BoardService;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


	

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService bService;
	
	@GetMapping("/{id}")
	public String boarDetails(@PathVariable("id") int bNo,Model model) {
		
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
	public String noticeView() {
		return "board/noticeView";
	}
	
	@GetMapping("noticeDetail")
	public String noticeDetail() {
		return "board/noticeDetail";
	}
	
	@GetMapping("project")
	public String project() {
		return "board/project";
	}

}

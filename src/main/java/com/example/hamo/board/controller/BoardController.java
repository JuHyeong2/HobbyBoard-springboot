package com.example.hamo.board.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hamo.board.model.service.BoardService;
import com.example.hamo.board.model.vo.Board;

import lombok.RequiredArgsConstructor;


	

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	private final BoardService bService;
	
	@GetMapping("boardDetails")
	public String boarDetails(@RequestParam("boardNo") int boardNo,Model model) {
		
		System.out.println("boardNo : " + boardNo); //테스트
		
		Board board = bService.selectBoard(boardNo);
		System.out.println(board); 					//테스트
		model.addAttribute("board", board);
		
		return "board/boardDetails";
	}
	
	@GetMapping("boardInsert")
	public String boardInsert() {
		return "board/boardInsert"; 
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
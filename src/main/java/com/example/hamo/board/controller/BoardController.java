package com.example.hamo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	@GetMapping("/board/boardDetails")
	public String boarDetails() {
		return "board/boardDetails";
	}
	
	@GetMapping("/board/boardInsert")
	public String boardInsert() {
		return "board/boardInsert"; 
	}
	
	@GetMapping("board/noticeView")
	public String noticeView() {
		return "board/noticeView";
	}
	
	@GetMapping("board/noticeDetail")
	public String noticeDetail() {
		return "board/noticeDetail";
	}

}

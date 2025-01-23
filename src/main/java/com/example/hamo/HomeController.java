package com.example.hamo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.hamo.board.model.service.BoardService;
import com.example.hamo.board.model.vo.Board;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final BoardService bService;
	
	@GetMapping("/")
	public String main(Model model) {
		
		ArrayList<Board> list = bService.selectBoardList();
		System.out.println("list : " + list);
		
		model.addAttribute("list", list);
		
		return "index";
	}
}
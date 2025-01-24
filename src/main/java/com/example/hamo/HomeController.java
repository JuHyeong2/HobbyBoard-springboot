package com.example.hamo;

import java.util.ArrayList;
import java.util.HashMap;

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
		
		ArrayList<Board> participants = bService.participantsByBoard();
		System.out.println(participants);
		for(Board b : list) {
			for(Board b2 : participants) {
				if(b.getBoardNo() == b2.getBoardNo()) {
					b.setParticipants(b2.getParticipants());
				}
			}
		}
		
		model.addAttribute("list", list);
		
		return "index";
	}
}

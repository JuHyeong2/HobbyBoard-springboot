package com.example.hamo.board.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.hamo.board.model.mapper.BoardMapper;
import com.example.hamo.board.model.vo.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper mapper;

	public ArrayList<Board> selectBoardList() {
		
		return mapper.selectBoardList();
	}

	public Board selectBoard(int boardNo) {
		
		return mapper.selectBoard(boardNo);
	}
}

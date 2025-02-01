package com.example.hamo.board.model.service;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.mapper.BoardMapper;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Category;
import com.example.hamo.board.model.vo.Image;
import com.example.hamo.board.model.vo.Reply;
import com.example.hamo.member.model.vo.Member;

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
	public int boardInsert(Board b) {
		
		return mapper.boardInsert(b);
	}
	
	 public Admin selectNotice(Member m, int bId) {
		Admin a = mapper.selectNotice(bId);
		int result = mapper.updateNoticeCount(bId);
		if(result >0) {
			a.setViews(result);
		}
		return a;

	}




	public int imageInsert(ArrayList<Image> list) {
		
		return mapper.imageInsert(list);
	}

	public ArrayList<Image> selectImageList(int bNo) {
		
		return mapper.selectImageList(bNo);
	}

	public int insertReply(Reply r) {
		
		return mapper.insertReply(r);
	}

	public ArrayList<Reply> selectReplyList(int bNo) {
		
		return mapper.selectReplyList(bNo);
	}

	public ArrayList<Board> participantsByBoard() {
		
		return mapper.participantsByBoard();
	}

	public ArrayList<Image> selectUserImageList() {
		
		return mapper.selectUserImageList();
	}

	public ArrayList<Image> selectImageListBoard() {

		return mapper.selectImageListBoard();
	}

	public int updateBoardCount(Board board) {
		
		return mapper.updateBoardCount(board);
	}

	public int insertParticipant(HashMap<String, Integer> map) {
		
		return mapper.insertParticipant(map);
	}

	public int selectParticipant(HashMap<String, Integer> map) {
		
		return mapper.selectParticipant(map);
	}
	public int insertLog(HashMap<String, String> map) {
		return mapper.insertLog(map);
	}
	
	public ArrayList<Category> selectCategory() {
		return mapper.selectCategory();
	}

	public ArrayList<Board> boardCategory(int categoryNo) {
		return mapper.boardCategory(categoryNo);
	}

	public ArrayList<Board> getBanner() {
		return mapper.getBanner();
	}

}


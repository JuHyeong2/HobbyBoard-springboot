package com.example.hamo.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.example.hamo.admin.model.mapper.AdminMapper;
import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.admin.model.vo.Dashboard;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.common.vo.PageInfo;
import com.example.hamo.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminMapper mapper;

	public int writeNotice(Admin admin) {
		return mapper.writeNotice(admin);
	}

	public ArrayList<Admin> selectNoticeList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit()); 
		return mapper.selectNoticeList(rowBounds);
	}

	public int getListCount() {
		return mapper.getListCount();
	}

	public Admin selectNoticeOne(int id) {
		return mapper.selectNoticeOne(id);
	}

	public int updateNotice(Admin admin) {
		return mapper.updateNotice(admin);
	}

	public int deleteNotice(int noticeId) {
		return mapper.deleteNotice(noticeId);
	}

	public ArrayList<Member> selectAllMember() {
		return mapper.selectAllMember();
	}



	public ArrayList<Board> selectAllBoardList() {
		return mapper.selectAllBoardList();
	}

	public int changeStatus(HashMap<String, String> map) {
		return mapper.changeStatus(map);
	}

	public ArrayList<Member> searchUserList(String searchValue) {
		return mapper.searchUserList(searchValue);
	}

	public int deleteBoard(int boardNo) {
		return mapper.deleteBoard(boardNo);
	}

	public ArrayList<Board> searchBoardList(HashMap<String, String> map) {	
		return mapper.searchBoardList(map);
	}

	

	





	public int deleteUser(int violatorNo) {
		return mapper.deleteUser(violatorNo);
	}



	public ArrayList<Dashboard> boardCount() {
		return mapper.boardCount();
	}

	public ArrayList<Dashboard> userCount() {
		return mapper.userCount();
	}

	public ArrayList<Dashboard> dailyUserCount() {
		// TODO Auto-generated method stub
		return mapper.dailyUserCount();
	}

}
package com.example.hamo.admin.model.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.member.model.vo.Member;

@Mapper
public interface AdminMapper { 

	int writeNotice(Admin admin);

	ArrayList<Admin> selectNoticeList(RowBounds rowBounds);

	int getListCount();

	Admin selectNoticeOne(int id);

	int updateNotice(Admin admin);

	int deleteNotice(int noticeId);

	ArrayList<Member> selectAllMember();



	ArrayList<Board> selectAllBoardList();

	int changeStatus(HashMap<String, String> map);

	ArrayList<Member> searchUserList(String searchValue);
}

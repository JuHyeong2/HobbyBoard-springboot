package com.example.hamo.admin.model.mapper;

import java.util.ArrayList;
<<<<<<< HEAD

import org.apache.ibatis.annotations.Mapper;

import com.example.hamo.admin.model.vo.Admin;
=======
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.member.model.vo.Member;
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba

@Mapper
public interface AdminMapper { 

	int writeNotice(Admin admin);

<<<<<<< HEAD
	ArrayList<Admin> selectNoticeList();
}
=======
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
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba

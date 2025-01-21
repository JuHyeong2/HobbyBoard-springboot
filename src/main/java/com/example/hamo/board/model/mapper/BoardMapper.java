package com.example.hamo.board.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.vo.Board;

@Mapper
public interface BoardMapper {

	ArrayList<Board> selectBoardList();

	Board selectBoard(int boardNo);

<<<<<<< HEAD
	int boardInsert(Board b);
=======
	Admin selectNotice(int bId);

	int updateNoticeCount(int bId);
>>>>>>> 1d8a7baad3fa21d37c3ff3e345117920d381b651

}

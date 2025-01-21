package com.example.hamo.board.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.vo.Board;

@Mapper
public interface BoardMapper {

	ArrayList<Board> selectBoardList();

	Board selectBoard(int boardNo);

	Admin selectNotice(int bId);

	int updateNoticeCount(int bId);

}

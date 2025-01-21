package com.example.hamo.board.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.hamo.board.model.vo.Board;

@Mapper
public interface BoardMapper {

	ArrayList<Board> selectBoardList();

	Board selectBoard(int boardNo);

	int boardInsert(Board b);

}

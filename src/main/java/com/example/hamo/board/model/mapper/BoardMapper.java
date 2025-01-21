package com.example.hamo.board.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

<<<<<<< HEAD
=======
import com.example.hamo.admin.model.vo.Admin;
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
import com.example.hamo.board.model.vo.Board;

@Mapper
public interface BoardMapper {

	ArrayList<Board> selectBoardList();

	Board selectBoard(int boardNo);

<<<<<<< HEAD
}
=======

	int boardInsert(Board b);

	Admin selectNotice(int bId);

	int updateNoticeCount(int bId);


}
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba

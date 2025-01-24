package com.example.hamo.board.model.mapper;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Image;
import com.example.hamo.board.model.vo.Reply;

@Mapper
public interface BoardMapper {

	ArrayList<Board> selectBoardList();

	Board selectBoard(int boardNo);


	int boardInsert(Board b);

	Admin selectNotice(int bId);

	int updateNoticeCount(int bId);

	int imageInsert(ArrayList<Image> list);

	ArrayList<Image> selectImageList(int bNo);

	int insertReply(Reply r);

	ArrayList<Reply> selectReplyList(int bNo);

	ArrayList<Board> participantsByBoard();

	ArrayList<Image> selectUserImageList();

	ArrayList<Image> selectImageListBoard();

}


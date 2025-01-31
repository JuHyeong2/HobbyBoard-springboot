package com.example.hamo.member.model.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.hamo.board.model.vo.Board;
import com.example.hamo.board.model.vo.Image;
import com.example.hamo.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	int insertMember(Member member);

	int idCheck(String userId);

	Member login(Member m);

	Member findId(int phone);

	Member selectMember(String id);

//	int updateMember(Member m);
	
	Member findId(HashMap<String, String> map);

	int updatePwd(Member m);


	ArrayList<Board> selectMyActivite(String id);

	ArrayList<Board> selectMyPost(String memberId);

	int updateMember(Member member);

	Member memberId(String memberId);

	int acceptParticipant(@Param("boardNo")int boardNo, @Param("participantId")int participantId);

	int rejectParticipant(@Param("boardNo")int boardNo, @Param("participantId")int participantId);

	ArrayList<Member> participants(int boardNo);

	int checknickName(String nickname);

	Image getProfileImage(int memberNo);

	int insertProfileImage(Image image);

	int updateProfileImage(Image image);

	Image selectImage(int memberNo);

	String getParticipantStatus(@Param("boardNo") int boardNo, @Param("participantId") int participantId);

	int updateBoardStatus(int boardNo);

	Board getBoardInfo(int boardNo);

	int getAcceptedParticipantsCount(int boardNo);


}
package com.example.hamo.member.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.hamo.board.model.vo.Board;
import com.example.hamo.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	int insertMember(Member member);

	int idCheck(String userId);

	Member login(Member m);

	Member findId(int phone);

	Member selectMember(String id);

//	int updateMember(Member m);

	ArrayList<Board> selectMyActivite(String id);

	ArrayList<Board> selectMyPost(String memberId);

	int updateMember(Member member);

//	Member memberId(String memberId);

	boolean accept(int boardNo, String participantId);

	boolean reject(int boardNo, String participantId);

	ArrayList<Member> participants(int boardNo);

	

//	String checkPwd(String memberId);


}
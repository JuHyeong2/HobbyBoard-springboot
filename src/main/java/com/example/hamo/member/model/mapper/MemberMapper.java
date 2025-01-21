package com.example.hamo.member.model.mapper;

<<<<<<< HEAD
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.hamo.board.model.vo.Board;
=======
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
import com.example.hamo.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	int insertMember(Member member);

	int idCheck(String userId);

	Member login(Member m);

<<<<<<< HEAD
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
=======
	Member findId(HashMap<String, String> map);

	int updatePwd(Member m);

}
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba

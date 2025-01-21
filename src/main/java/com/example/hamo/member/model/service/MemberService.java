package com.example.hamo.member.model.service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;
=======
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.example.hamo.member.model.mapper.MemberMapper;
import com.example.hamo.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper mMapper;
	
	public int insertMember(Member member) {
		
		return mMapper.insertMember(member);
	}

	public int idCheck(String userId) {
		
		return mMapper.idCheck(userId);
	}

	public Member login(Member m) {
		
		return mMapper.login(m);
	}

	public Member findId(HashMap<String, String> map) {
		
		return mMapper.findId(map);
	}

	public int updatePwd(Member m) {
		
		return mMapper.updatePwd(m);
	}
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba

import org.springframework.stereotype.Service;

import com.example.hamo.board.model.vo.Board;
import com.example.hamo.member.model.mapper.MemberMapper;
import com.example.hamo.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper mMapper;
	
	
	public int insertMember(Member member) {
		
		return mMapper.insertMember(member);
	}

	public int idCheck(String userId) {
		
		return mMapper.idCheck(userId);
	}

	public Member login(Member m) {
		
		return mMapper.login(m);
	}

	public Member findId(int phone) {
		
		return mMapper.findId(phone);
	}

	public Member selectMember(String id) {
		return mMapper.selectMember(id);
	}

//	public int updateMember(Member m) {
//		return mMapper.updateMember(m);
//	}

	public ArrayList<Board> selectMyActivite(String id) {
		return mMapper.selectMyActivite(id);
	}

	public ArrayList<Board> selectMyPost(String memberId) {
		return mMapper.selectMyPost(memberId);
	}

	public int updateMember(Member member) {
		return mMapper.updateMember(member);
	}

//	public Member memberId(String memberId) {
//		return mMapper.memberId(memberId);
//	}

	public boolean handleParticipant(String action, int boardNo, String participantId) {
		if("a".equals(action)) {
			return mMapper.accept(boardNo,participantId);
		}else if("r".equals(action)){
			return mMapper.reject(boardNo,participantId);
			
		}
		return false;
	}

	public ArrayList<Member> participants(int boardNo) {
		return mMapper.participants(boardNo);
	}




}
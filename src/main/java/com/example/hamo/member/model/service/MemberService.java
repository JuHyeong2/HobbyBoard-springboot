package com.example.hamo.member.model.service;

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

}

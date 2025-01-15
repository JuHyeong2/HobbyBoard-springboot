package com.example.hamo.member.model.service;

import org.springframework.stereotype.Service;

import com.example.hamo.member.model.mapper.MemberMapper;
import com.example.hamo.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private MemberMapper mMapper;
	
	public int insertMember(Member member) {
		
		return mMapper.insertMember(member);
	}

}

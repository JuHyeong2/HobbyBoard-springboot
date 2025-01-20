package com.example.hamo.member.model.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.hamo.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	int insertMember(Member member);

	int idCheck(String userId);

	Member login(Member m);

	Member findId(HashMap<String, String> map);

}

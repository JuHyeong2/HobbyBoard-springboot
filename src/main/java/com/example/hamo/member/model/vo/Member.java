	package com.example.hamo.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
	private int memberNo;
	private String memberName;
	private String memberId;
	private String memberPwd;
	private Date memberBirth;
	private String memberGender;
	private String memberPhone;
	private String memberEmail;
	private String memberNickname;
	private String status;
	private Date createDate;
	private String isAdmin;
	private String imageUrl;
}




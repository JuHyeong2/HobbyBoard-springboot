package com.example.hamo.admin.model.vo;

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
public class Admin {
	private int boardNo;
	private String title;
	private String content;
	private Date createDate;
	private Date updateDate;
	private int views;
	private String adminBoardStatus;
	private int writer;
	private String memberNickname;
}
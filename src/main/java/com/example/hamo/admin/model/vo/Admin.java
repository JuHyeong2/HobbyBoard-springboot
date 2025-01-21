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
<<<<<<< HEAD
}
=======
	private String memberNickname;
}
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba

package com.example.hamo.board.model.vo;

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
public class Reply {
	private int replyNo;
	private String replyStatus;
	private String replyContent;
	private Date createDate;
	private Date updateDate;
	private int memberNo;
	private int boardNo;
	private String memberNickname;
}

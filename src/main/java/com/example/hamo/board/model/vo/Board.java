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
public class Board {
	private int boardNo;
	private String title;
	private String content;
	private Date createDate;
	private Date updateDate;
	private int views;
	private int maxParticipants;
	private String boardStatus;
	private int memberNo;
	private int locationNo;
	private String locationName;
	private int categoryNo;
	private String categoryName;
	private String memberNickName;
}

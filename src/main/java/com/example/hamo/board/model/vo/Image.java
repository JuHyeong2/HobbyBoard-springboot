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
public class Image {
	private int imgNo;
	private String imgName;
	private String imgPath;
	private String imgRename;
	private String delimiter;
	private int buNo;
	private String url;
}

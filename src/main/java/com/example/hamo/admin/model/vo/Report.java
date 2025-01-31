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
public class Report {
	private int reportNo;
	private String reportText;
	private Date reportDate;
	private int reporter;
	private int violator;
	private int boardNo;
	private char reportStatus; 
	private String reporterNick;
	private String violatorNick;
}

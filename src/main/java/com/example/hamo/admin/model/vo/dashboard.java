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
public class dashboard {
	private Date createDate;
	private Date visitDate;
	private int id;
	private int count;
	private String ipAddress;
	private String userAgent;
}

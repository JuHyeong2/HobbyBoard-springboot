package com.example.hamo.admin.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.hamo.admin.model.mapper.AdminMapper;
import com.example.hamo.admin.model.vo.Admin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminMapper mapper;

	public int writeNotice(Admin admin) {
		return mapper.writeNotice(admin);
	}

	public ArrayList<Admin> selectNoticeList() {
		return mapper.selectNoticeList();
	}

}

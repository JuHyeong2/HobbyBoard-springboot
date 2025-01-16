package com.example.hamo.admin.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.example.hamo.admin.model.mapper.AdminMapper;
import com.example.hamo.admin.model.vo.Admin;
import com.example.hamo.common.vo.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final AdminMapper mapper;

	public int writeNotice(Admin admin) {
		return mapper.writeNotice(admin);
	}

	public ArrayList<Admin> selectNoticeList(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit()); 
		return mapper.selectNoticeList(rowBounds);
	}

	public int getListCount() {
		return mapper.getListCount();
	}

	public Admin selectNoticeOne(int id) {
		return mapper.selectNoticeOne(id);
	}

}

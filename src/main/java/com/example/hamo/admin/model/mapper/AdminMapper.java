package com.example.hamo.admin.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.hamo.admin.model.vo.Admin;

@Mapper
public interface AdminMapper { 

	int writeNotice(Admin admin);

	ArrayList<Admin> selectNoticeList(RowBounds rowBounds);

	int getListCount();

	Admin selectNoticeOne(int id);
}

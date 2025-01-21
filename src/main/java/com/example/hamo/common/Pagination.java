package com.example.hamo.common;

import com.example.hamo.common.vo.PageInfo;

public class Pagination {
	public static PageInfo getPageInfo(int currentPage, int listCount, int boardLimit) {
		int pageLimit = 10;
		// currentPage 현재 페이지, listCount 전체 글 개수,
		
		// boardLimit 한 페이지에 들어갈 수 있는 글 개수
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);

		
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		int endPage = startPage + pageLimit - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		return new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit);
	}
}
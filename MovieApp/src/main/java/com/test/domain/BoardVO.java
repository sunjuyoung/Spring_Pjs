package com.test.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	
	//리스트 목록
	private String title,content,writer;
	private int bno;
	private Date updatedate,regdate;
	
	
	//페이지
	private int pageNum,amount;

	//검색
	private String keyword , type;
	
	//댓글 수
	private int replyCnt;
	

	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
}

package com.test.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	//리스트 목록
	private String title,content,writer;
	private int bno;
	
	/* @DateTimeFormat(pattern = "yyyy-MM-dd")  파라미터로 사용되는 인스턴스 변수에 적용가능*/ 
	private Date updateDate,regDate;
	
	
	
	//검색
	private String keyword , type;
	
	//댓글 수
	private int replyCnt;
	//페이지
	private int pageNum,amount;

	
}

package com.test.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	//게시물 번호
	private Long bno;
	
	//제목
	private String title;
	//내용
	private String content;
	
	//날짜
	private Date regDate,updateDate;
	//작성자
	private String writer;
	
	//댓글 수
	private int replyCnt;
	
	
}

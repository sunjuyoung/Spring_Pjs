package com.test.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReplyVO {

	private int rno ,bno;
	
	private String reply;
	private String replyer;
	
	private Date replyDate;
	private Date updateDate;
	
	
	
}

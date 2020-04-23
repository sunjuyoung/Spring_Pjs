package com.test.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {

	
	private Long rno;  //pk
	private Long bno; //fk
	
	private String reply; 
	private String replyer;
	private Date replyDate;
	private Date updateDate;
	
	
	
	
}

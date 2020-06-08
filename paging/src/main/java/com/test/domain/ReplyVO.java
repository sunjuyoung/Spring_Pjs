package com.test.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	
	private int rno;
	private int bno;
	private String reply,replyer;
	private Date replyDate,updateDate;

}

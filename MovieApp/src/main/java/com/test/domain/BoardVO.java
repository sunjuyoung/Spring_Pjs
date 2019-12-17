package com.test.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private String title,content,writer;
	private int bno;
	private Date updatedate,regdate;
	
	private int pageNum,amount;

}

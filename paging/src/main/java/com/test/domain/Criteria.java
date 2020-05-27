package com.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Criteria {
	
	private int pageNum;
	private int amount;
	private int offset;
	

	
	public Criteria(int pageNum,int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
		
		this.offset = (pageNum -1) * amount;
	}

}
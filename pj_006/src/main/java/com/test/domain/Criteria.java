package com.test.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data

public class Criteria {

	//페이지 번호,한페이지 목록 수 
	private int pageNum;
	private int amount;
	
	//검색
	private String type;
	private String keyword;
	



	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		
		
		
		
	}

	
	
	
}

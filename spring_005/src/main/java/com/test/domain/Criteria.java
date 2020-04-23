package com.test.domain;

import lombok.Data;

@Data
public class Criteria {
	
	//페이지
	private int pageNum,amount; 
	//검색조건
	private String type,keyword; 
	
	
	  public Criteria() { this(1,10); }
	
	  

	
	public Criteria(int pageNum,int amount) {
		this.pageNum=pageNum;
		this.amount= amount;
	}

	
	//검색조건이 각 글자 T,W,C로 구성되어있으므로 검색 조건을 배열로 만들어서 한 번에 처리하기 위함
	public String[] getTypeArr() {
		
		return type == null? new String[] {}: type.split("");
	}




	
}

package com.test.domain;

import lombok.Data;

@Data
public class PageDTO {

	
	private int pageNum,amount,total,offset;
	private boolean next,prev;
	
	private String keyword , type;
	
	
	private int startPage,endPage;
	
	
	public PageDTO(int pageNum, int amount,int total) {
		this.amount = amount;
		this.pageNum = pageNum;
		this.total = total;
		
		
		this.offset = (pageNum -1) * amount;
	
		//1~10페이지까지 10
		//11페이지부터 20
		this.endPage = (int)(Math.ceil(pageNum/10.0)) * 10;
		
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil(total*1.0)/amount);
		
		if(endPage > realEnd) {
			endPage = realEnd;
		}
		
		this.prev = this.pageNum > 1;
		this.next = this.pageNum < endPage;
		
		
	}
	
	
}

package com.test.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
	private boolean next; 
	private boolean prev;
	
	private int endPage,startPage,realPage;
	
	private int total;
	
	private Criteria cri;
	
	/* private int offset; */
	
	/**
	 * 
	 * @param cri
	 * @param total
	 */
	public PageDTO(Criteria cri,int total) {
		this.cri = cri;
		this.total=total;
		
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)*10);
		
		this.startPage = this.endPage -9;
		
		this.realPage = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		if(this.realPage < this.endPage) {
			this.endPage = this.realPage;
		}
		
		this.prev = this.startPage > 1;
		
		this.next = this.endPage < this.realPage;
		
	}
	
	

}

package com.test.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j;
//페이징 계산
@Data
@Log4j
public class PageDTO {

	
	private int startPage,endPage,total;
	
	private Criteria cri;
	
	private boolean prev,next;
	
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		log.info("------------------------------"+cri.getPageNum());
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		log.info("------------------------------"+this.endPage);
		
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
			
		}
		
		this.prev = this.startPage > 1;
		
		this.next  = this.endPage < realEnd;
		
		
		
		
	}
	
	
	
}

package com.test.domain;

import lombok.Data;

@Data
public class PagingDTO {

	private int startPage;
	private int endPage;

	private int total;

	private boolean prev;
	private boolean next;
	
	private Criteria cri;

	public PagingDTO(Criteria cri, int total) {

		
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		
		this.startPage = endPage - 9;

		int realEnd = (int) (Math.ceil(total * 1.0) / cri.getAmount());

		if (this.endPage > realEnd) {
			this.endPage = realEnd;
		}

		this.prev = this.startPage > 1;

		this.next = this.endPage < realEnd;

	}

}

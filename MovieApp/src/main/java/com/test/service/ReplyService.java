package com.test.service;

import com.test.domain.ReplyVO;

public interface ReplyService {
	
	public ReplyVO list(int bno);
	
	public int insert(ReplyVO vo);
	
	public int delete(int rno);
	
	

}

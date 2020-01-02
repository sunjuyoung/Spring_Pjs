package com.test.service;

import java.util.List;

import com.test.domain.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> list(int bno);
	
	public int insert(ReplyVO vo);
	
	public int delete(int rno);
	
	public int total(int bno);

}

package com.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.domain.PageDTO;
import com.test.domain.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> list(int bno);
	
	public int insert(ReplyVO vo);
	
	public int delete(int rno);
	
	public int total(int bno);
	
	public List<ReplyVO> getListWithPaging( PageDTO dto, int bno);

}

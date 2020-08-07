package com.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.domain.Criteria;
import com.test.domain.ReplyVO;

public interface ReplyService {

	public int insert(ReplyVO vo);
	
	public List<ReplyVO> list(@Param("bno")int bno,@Param("cri") Criteria cri);
	
	public int delete(int rno);
	
	public int modify(ReplyVO vo);
}

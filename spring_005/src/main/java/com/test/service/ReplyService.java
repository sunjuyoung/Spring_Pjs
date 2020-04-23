package com.test.service;

import com.test.domain.Criteria;
import com.test.domain.ReplyPageDTO;
import com.test.domain.ReplyVO;

public interface ReplyService {
	
	public int insert(ReplyVO vo);
	
	public ReplyPageDTO getListWithPaging( Long bno,Criteria cri);
	
	public int update(ReplyVO vo);

	public int remove (Long rno);
	
	public ReplyVO read(Long rno);

}

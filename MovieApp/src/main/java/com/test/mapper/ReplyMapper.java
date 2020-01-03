package com.test.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.domain.PageDTO;
import com.test.domain.ReplyVO;

public interface ReplyMapper {
	
	public List<ReplyVO> list(int bno);
	
	public int insert(ReplyVO vo);
	
	public int delete(int rno);
	
	
	public List<ReplyVO> getListWithPaging(@Param("dto") PageDTO dto,@Param("bno") int bno);
	
	public int total(int bno);

}

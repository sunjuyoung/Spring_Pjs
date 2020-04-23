package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.domain.Criteria;
import com.test.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	
	//mybatis 2개이상 데이터를 파라미터를 전달 별도의 객체,map param
	//param속성값은 mybatis sql을 이용해서 #{}의 이름으로 사용가능
	public List<ReplyVO> getListWithPaging(@Param("bno") Long bno,@Param("cri")Criteria cri);
	
	public int update(ReplyVO vo);

	public int remove (Long rno);
	
	public ReplyVO read(Long rno);
	
	public int getCountByBno(@Param("bno") Long bno);
	
	
}

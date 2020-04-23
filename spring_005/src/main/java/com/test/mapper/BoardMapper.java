package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	//리스트
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//추가
	public void insert (BoardVO board);
	
	//mybatis selectkey 를 이용한 추가
	public void insertSelectKey(BoardVO board);
	
	//
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int modify(BoardVO board);

	
	public int total(Criteria cri);
	
	//해당게시물 번호와 증가,감소를 의미하는 amount파라미터로 받는다
	public void updateReplyCnt(@Param("bno")Long bno, @Param("amount")int amount);
	
	
	
	
	
	
	
	
	
	
}

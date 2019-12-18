package com.test.mapper;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.PageDTO;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	public BoardVO get(int bno);
	
	public void insert (BoardVO board);
	
	
	public int delete(int bno);
	
	public int modify(BoardVO board);

	
	public List<BoardVO> getListWithPaging(PageDTO dto);
	
	public int total();
	/*
	//리스트
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//추가

	
	//mybatis selectkey 를 이용한 추가
	public void insertSelectKey(BoardVO board);
	
	//



	

	
	//해당게시물 번호와 증가,감소를 의미하는 amount파라미터로 받는다
	public void updateReplyCnt(@Param("bno")Long bno, @Param("amount")int amount);
	
	
	
	
	
	*/
	
	
	
	
}

package com.test.service;

import java.util.List;

import com.test.domain.BoardVO;


public interface BoardService {
	
	public List<BoardVO> getList();
	
	public BoardVO get(Long bno);
	
	/*
	public List<BoardVO> getListWithPaging(Criteria cri);

	public void register(BoardVO board);
		
	
	
	public boolean remove(Long bno);
	
	public int modify(BoardVO board);
	
	
	
	public int total(Criteria cri);
	
	*/
	
}

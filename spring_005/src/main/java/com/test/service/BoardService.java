package com.test.service;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;


public interface BoardService {
	
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);

	public void register(BoardVO board);
		
	public BoardVO get(Long bno);
	
	public boolean remove(Long bno);
	
	public int modify(BoardVO board);
	
	
	
	public int total(Criteria cri);
	
}

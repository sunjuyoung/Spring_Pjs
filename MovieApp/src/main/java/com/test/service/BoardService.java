package com.test.service;

import java.util.List;

import com.test.domain.BoardAttachVO;
import com.test.domain.BoardVO;
import com.test.domain.PageDTO;


public interface BoardService {
	
	public List<BoardVO> getList();
	
	public BoardVO get(int bno);
	
	public void insert(BoardVO board);
	
	public void insertWithFile(BoardVO board);
	
	public boolean remove(int bno);
	
	public int modify(BoardVO board);
	
	
	public List<BoardVO> getListWithPaging(PageDTO dto);
	
	public int total();
	/*
	public List<BoardVO> getListWithPaging(Criteria cri);

	public void register(BoardVO board);
		
		
	*/
		
	public void fileInsert(BoardVO vo);
	
	public void fileDelete(String uuid);
	
	public List<BoardVO> findByBno(int bno);
	
	
	
	
	

	
}

package com.test.service;

import java.util.List;

import com.test.domain.BoardVO;

public interface BoardService {
	
	
	public List<BoardVO> boardList();
	
	public int insert(BoardVO vo);

	public int delete(int bno);
}

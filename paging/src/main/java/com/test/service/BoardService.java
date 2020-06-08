package com.test.service;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getList(Criteria cri);

	public int total();
	
	public void insert (BoardVO board);
	
	
	public BoardVO get(int bno);
}

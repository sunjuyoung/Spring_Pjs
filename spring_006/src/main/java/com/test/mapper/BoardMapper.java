package com.test.mapper;

import java.util.List;

import com.test.domain.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> boardList();
	
	public int insert(BoardVO vo);
	
	public int delete(int bno);

}

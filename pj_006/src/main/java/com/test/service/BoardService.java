package com.test.service;

import java.util.List;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;


public interface BoardService {
	
	
	//리스트
	public List<BoardVO> getList();
	
	//페이징 리스트
	public List<BoardVO> pagingList(Criteria cri);
	
	
	//조회
	public BoardVO read(Long bno);
	
	
	//삭제
	public int remove(Long bno);
	

	//추가
	public int insert(BoardVO vo);
	
	//수정
	public int update(BoardVO vo);
	
	
	
	//total
	public int total();
	
	
}

package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;

public interface BoardMapper {
	
	
	//리스트
	public List<BoardVO> boardList();
	
	//페이징리스트
	public List<BoardVO> pagingList(Criteria cri);
	
	
	//페이징리스트
	public List<BoardVO> list(@Param("cri")Criteria cri,@Param("map")Map<String, String> map);
	
	
	//조회
	public BoardVO read(Long bno);

	//삭제
	public int delete(Long bno);
	
	//추가
	public int insert (BoardVO board);
	
	//mybatis selectkey 를 이용한 추가
	public int insertSelectKey(BoardVO board);
	
	
	//수정
	public int update(BoardVO vo);

	
	//total
	public int total();
	
	
	
	
	
	
}

package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.BoardVO;
import com.test.mapper.BoardMapper;

import lombok.Setter;

@Service
class BoardServiceImpl implements BoardService {
	
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public List<BoardVO> boardList() {
		return mapper.boardList();
	}

	@Override
	public int insert(BoardVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int delete(int bno) {
		return mapper.delete(bno);
	}

}

package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.mapper.BoardMapper;

@Service

public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		return mapper.getListWithPaging(cri);
	}



	@Override
	public int total() {
		// TODO Auto-generated method stub
		return mapper.total();
	}



	@Override
	public void insert(BoardVO board) {
		// TODO Auto-generated method stub
		
		mapper.insert(board);
	}



	@Override
	public BoardVO get(int bno) {
		// TODO Auto-generated method stub
		return mapper.get(bno);
	}

}

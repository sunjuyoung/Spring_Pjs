package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.Criteria;
import com.test.domain.ReplyVO;
import com.test.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{

	
	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int insert(ReplyVO vo) {
		return mapper.insert(vo);
	}



	@Override
	public int delete(int rno) {
		// TODO Auto-generated method stub
		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.modify(vo);
		
	}



	@Override
	public List<ReplyVO> list(int bno, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.list(bno, cri);
	}

}

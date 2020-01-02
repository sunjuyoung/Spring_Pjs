package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.ReplyVO;
import com.test.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public List<ReplyVO> list(int bno) {
		return mapper.list(bno);
	}

	@Override
	public int insert(ReplyVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int delete(int rno) {
		return mapper.delete(rno);
	}

	@Override
	public int total(int bno) {
		return mapper.total(bno);
	}

}

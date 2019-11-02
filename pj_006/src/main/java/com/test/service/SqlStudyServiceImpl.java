package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.SqlVO;
import com.test.mapper.SqlStudyMapper;

@Service
public class SqlStudyServiceImpl implements SqlStudyService{

	
	@Autowired
	private SqlStudyMapper mapper;
	
	@Override
	public SqlVO testList() {
		return mapper.testList();
	}

}

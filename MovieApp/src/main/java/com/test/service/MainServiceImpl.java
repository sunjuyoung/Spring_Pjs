package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.MainVO;
import com.test.mapper.MainMapper;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	private MainMapper mapper;
	
	@Override
	public MainVO main() {
		
		return mapper.main();
	}

}

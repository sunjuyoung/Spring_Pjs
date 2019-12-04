package com.test.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.MainMapper;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	private MainMapper mapper;
	
	@Override
	public Map main() {
		
		return mapper.main();
	}

}

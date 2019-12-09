package com.test.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class SampleVOMap {

	private SampleVO vo;
	
	private Map<String,SampleVO> map;
	
	public SampleVOMap() {
		
		map = new HashMap<>();
		
		
	}
}

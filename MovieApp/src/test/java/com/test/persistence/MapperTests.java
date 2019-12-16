package com.test.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.mapper.MainMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTests {

	
	@Setter(onMethod_ = {@Autowired})
	private MainMapper mapper;
	
	@Test
	public void mapperTest() {
		
		//실제 동작하는 클래스의 이름을 확인
		log.info(mapper.getClass().getName());
		log.info(mapper.main());
	}
	
}

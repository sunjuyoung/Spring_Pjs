package com.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleServiceTests {
	
	@Autowired
	private SampleService service;
	
	@Test
	public void aopTest() {
		log.info(service);
		log.info(service.getClass().getName()); //$Proxy  JDK의 다이나믹프록시 
		
	}
	
	
	@Test
	public void testAdd()throws Exception {
		log.info(service.doAdd("123","abc"));
		
	}
	

	

}

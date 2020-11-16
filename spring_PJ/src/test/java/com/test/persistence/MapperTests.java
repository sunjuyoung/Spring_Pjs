package com.test.persistence;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTests {
	
	/*
	 * @Setter(onMethod_ = @Autowired) private TestMapper mapper;
	 * 
	 * @Test public void testTime() { log.info(mapper.getTime());
	 * log.info(mapper.getClass().getName()); }
	 */

}

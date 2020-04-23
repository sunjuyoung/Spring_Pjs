package com.test.mapper;

import java.util.stream.IntStream;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

@Log4j
public class ReplyMapperTests {

	@Inject
	private ReplyMapper mapper;
	
	
	
	@Test
	public void testMapper() {
		
		IntStream.rangeClosed(1,10).forEach(i->{
			
			ReplyVO vo = new ReplyVO();
			vo.setBno(121L);
			vo.setReply("test011111"+i);
			vo.setReplyer("tester01"+i);
			
			mapper.insert(vo);
			
		});
		
		
	}
	
}

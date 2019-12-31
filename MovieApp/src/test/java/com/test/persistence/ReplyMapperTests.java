package com.test.persistence;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.ReplyVO;
import com.test.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		
		IntStream.rangeClosed(1, 10).forEach(i->{
			log.info(i);
		});
		
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(41);
		vo.setReply("test");
		vo.setReplyer("관리자90");
		
		mapper.insert(vo);
		
		log.info(mapper);
	}
	
	

}

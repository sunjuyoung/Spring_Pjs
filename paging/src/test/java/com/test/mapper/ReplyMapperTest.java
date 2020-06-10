package com.test.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.domain.Criteria;
import com.test.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTest {
	
	@Autowired
	private ReplyMapper mapper;
	
	/*
	@Test
	public void insertMapper() {
		
		IntStream.range(1, 10).forEach(i->{
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(41);
			vo.setReplyer("관리자90");
			vo.setReply("테스트입니다"+i);
			
			mapper.insert(vo);
		});
	}
	*/
	
	/*
	@Test
	public void deleteMapper() {
		int count =mapper.delete(21);
		log.info("delete"+count);
	}
	*/
	
	@Test
	public void listMapper() {
		int bno = 41;
		
		Criteria cri = new Criteria();
		
		List<ReplyVO> list = mapper.list(bno,cri);
		
		
		list.forEach(reply -> log.info("------=========------"+reply));
		
	}
	
	/*
	@Test
	public void modify() {
		ReplyVO vo = new ReplyVO();
		vo.setReply("수정 테스트");
		vo.setRno(19);
		
		int count = mapper.modify(vo);
		log.info("modify"+count);
		
	}
*/
}

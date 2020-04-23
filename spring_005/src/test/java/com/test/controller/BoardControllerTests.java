package com.test.controller;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//기존 테스트코드와 다르다
//매번 URL테스트하기위해 tomcat과 같은 WAS 실행하는 불편한 단계를 생략하기위해서이다
//controller를 테스트할때는 고려해볼만하다

@RunWith(SpringJUnit4ClassRunner.class)

// Test for Controller
@WebAppConfiguration

@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
// Java Config
// @ContextConfiguration(classes = {
// org.zerock.config.RootConfig.class,
// org.zerock.config.ServletConfig.class} )
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = { @Autowired })
	private BoardMapper mapper;

	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before //오류시 import문제 JUNIT으로 
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	
	
	@Test
	public void testSearch() {
		
		Criteria cri = new Criteria();
		cri.setKeyword("test");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	
	
	/*
	 * @Test public void testList() throws Exception {
	 * 
	 * log.info(
	 * mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().
	 * getModelAndView().getModelMap()); }
	 */
	
	/*
	 * @Test public void testRegister() throws Exception {
	 * 
	 * String resultPage = mockMvc
	 * .perform(MockMvcRequestBuilders.post("/board/register")
	 * .param("title","테스트 새글 제목") .param("content", "테스트 새글 내용") .param("writer",
	 * "user00")) .andReturn().getModelAndView().getViewName();
	 * 
	 * log.info(resultPage);
	 * 
	 * }
	 */
	  
	/*
	 * @Test public void testRemove()throws Exception{
	 * 
	 * String resultPage=
	 * mockMvc.perform(MockMvcRequestBuilders.post("/board/remove") .param("bno",
	 * "61")
	 * 
	 * ).andReturn().getModelAndView().getViewName(); log.info(resultPage); }
	 */
	/*
	 * @Test public void tetGet() throws Exception {
	 * 
	 * log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param(
	 * "bno", "2")).andReturn() .getModelAndView().getModelMap()); }
	 */
	  
	/*
	 * @Test public void testModify() throws Exception {
	 * 
	 * String resultPage = mockMvc
	 * .perform(MockMvcRequestBuilders.post("/board/modify").param("bno",
	 * "1").param("title", "수정된 테스트 새글 제목") .param("content",
	 * "수정된 테스트 새글 내용").param("writer", "user00"))
	 * .andReturn().getModelAndView().getViewName();
	 * 
	 * log.info(resultPage);
	 * 
	 * }
	 */
	  
	/*
	 * @Test public void testRemove() throws Exception { // 삭제전 데이터베이스에 게시물 번호 확인할 것
	 * String resultPage =
	 * mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno",
	 * "25")).andReturn() .getModelAndView().getViewName();
	 * 
	 * log.info(resultPage); }
	 */
	  
	/*
	 * @Test public void testListPaging() throws Exception {
	 * 
	 * log.info(mockMvc.perform( MockMvcRequestBuilders.get("/board/list")
	 * .param("pageNum", "2") .param("amount", "50"))
	 * .andReturn().getModelAndView().getModelMap()); }
	 */
	 

}



package com.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@WebAppConfiguration //Servlet의 servlet-context를 이용하기위해서인데 스프링에서는 WebAppConfiguration라는 존재를 사용하기 위해서입니다
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTest {

	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	//가짜mvc ,가짜로 URL과 파라미터등을 브라우저에서 사용하는 것처럼 만들어서 controller실행해 볼수 있습니다.
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	/*
	//리스트
	@Test
	public void testList() throws Exception {
		log.info(
			mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap()
												
												
												);
	}
	
	*/
	
	//등록
	@Test
	public void testregister() throws Exception {
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
												.param("title","테스트 새글")
												.param("content","테스트 새글 내용")
												.param("writer","user001")
												).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
		
		
		
	}
	
	
}

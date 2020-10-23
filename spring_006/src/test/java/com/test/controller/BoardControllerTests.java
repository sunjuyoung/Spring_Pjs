package com.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {

	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mvc;
	
	@Before
	public void be() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	
	
	@Test
	public void testList() throws Exception{
		
		
		log.info(
				mvc.perform(get("/board/main")).andReturn().getModelAndView().getModelMap()
				);
		
	}
	
	/*
	@Test
	public void testinsert() throws Exception{
		
		log.info(
				mvc.perform(MockMvcRequestBuilders.post("/board/insert").param("title","title test2")
						.param("content","content test2")
						.param("writer","writer3"))
				.andExpect(status().isOk())
				);
	}
	*/
	/*
	@Test
	public void testDelete() throws Exception{
		
		log.info(
				mvc.perform(MockMvcRequestBuilders.post("/board/delete").param("bno","2")).andExpect(status().isOk())
				
				);
	}
	*/
}

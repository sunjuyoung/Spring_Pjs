package com.test.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {

	//스프링에 빈으로 등록된 DataSource를 이용해서 connection을 제대로 처리할 수 있는지를 확인
	
	
	@Setter(onMethod_ =  {@Autowired} )
	private DataSource dataSource;
	
	
	//SqlSessionFactory
	@Setter(onMethod_ = {@Autowired})
	private SqlSessionFactory sql;
	
	
	@Test
	public void testConnection() {
		
		Connection conn =null;
		
		try {
			
			conn = dataSource.getConnection();
			
			SqlSession session = sql.openSession();
			
			conn = session.getConnection();
			
			log.info(session);
			log.info(conn);
			
			
		}catch(Exception e) {
			fail(e.getMessage());
			
		}
	}
}

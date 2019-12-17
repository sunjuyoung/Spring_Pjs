package com.test.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTests {

	
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper mapper;
	
	@Autowired
	private DataSource dataSource;
	
	
	@Test
	public void mapperTest() {
		
		//실제 동작하는 클래스의 이름을 확인
		//log.info(mapper.getClass().getName());
		//log.info(mapper.main());
		
		String sql = "INSERT INTO tb1_board (title,content,writer) VALUES (?,?,?)";
		
		
		for(int i=0; i<20; i++) {
		Connection conn =null;
		PreparedStatement  pstmt = null;
		
		try {
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,"테스트" + i);
			pstmt.setString(2, "테스트입니다"+i);
			pstmt.setString(3, "user"+i);
			
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			if(conn != null) {try{conn.close();}catch(Exception e){}}
			if(pstmt != null) {try{pstmt.close();}catch(Exception e){}}
		}
	}
		
	}
	
}

package com.test.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testconnect() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://nodedb.clwklshv9aej.ap-northeast-2.rds.amazonaws.com","ID","password");
			
			log.info(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

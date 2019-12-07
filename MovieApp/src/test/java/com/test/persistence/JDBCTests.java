package com.test.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testConnection() {
		
		Connection conn =null;
		
		try {
			conn =DriverManager.getConnection("jdbc:mysql://database-2.cdivqmcssv6e.ap-northeast-2.rds.amazonaws.com/innodb?" +
				                                   "user=sun&password=tnqls2356");
			
		/*	conn =DriverManager.getConnection("jdbc:oracle:thin:@localhot:1521:XE",
                    							"id","password");
			*/
			
			log.info(conn);
		}catch(Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	
}

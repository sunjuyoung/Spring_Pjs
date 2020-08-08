package com.test.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class MemberTests {
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwcoder;
	
	@Autowired
	private DataSource ds;
	
	@Test
	public void testInsert() {
		
		String sql = "insert into tbl_member(userid,userpw,username) values (?,?,?)";
		
		for(int i=101; i<110; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1,"user"+i);
				pstmt.setString(2, pwcoder.encode("1234"));
				pstmt.setString(3, "일반사용자"+i);
				
				pstmt.executeUpdate();
				pstmt.close();
			}catch(Exception e) {
				
			}finally {
				
				
			}
		}
		
	}

}

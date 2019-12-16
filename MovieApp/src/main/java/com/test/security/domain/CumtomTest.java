package com.test.security.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CumtomTest extends User {

	public CumtomTest(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		
		
		super(username, password, authorities);
	}
	
	

}

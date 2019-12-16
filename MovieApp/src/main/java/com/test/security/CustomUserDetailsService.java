package com.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.domain.MemberVO;
import com.test.mapper.MemberMapper;
import com.test.security.domain.CustomUser;

import lombok.extern.log4j.Log4j;

//JDBC와 약간의 쿼리를 이용하는 것만으로도 스프링시큐리티를 사용할수있음에도
//굳이 CustomUserDetailsService와 같이 별도의 인증/권한 체크를 하는 가장 큰이유는 JSP등에서 단순히 사용자의 아이디(username)
//정도가 아닌 이름이나 이메일과 같은 추가적인 정보를 이용하기 위해서입니다.
@Log4j
public class CustomUserDetailsService  implements UserDetailsService{ 
	
	@Autowired
	private MemberMapper mapper;

	
	//내부적으로 MemberMapper를 이용해서 MemberVO를 조회하고, 만일 MemberVO의 인스턴스를 얻을수 있다면
	//CustomUser타입의 객체로 변환해서 반환합니다.
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		log.warn("Load User by Username : " + userName);
		
		
		MemberVO vo = mapper.read(userName);
		
		log.warn("queried by member mapper : " + vo);
		
		
		
		return vo == null? null: new CustomUser(vo);
	}

	
}

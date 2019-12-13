package com.test.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.test.domain.MemberVO;

import lombok.Getter;

/*

UserDetailsService 리턴타입은 UserDetails라는 타입
MemberVO 를 UserDetails 타입으로 변환하는 작업을 처리해야한다
UserDetails를 구현한 User클래스를 상속하는 CustomUser클래스 생성




*/
@Getter
public class CustomUser extends User{ //userdetails.User클래스 상속하기때문에 부모 클래스 생성자를 호출해야만 정상적인 객체를 생성할수 있다
//MemberVO를 파라미터로 전달해서 User클래스에 맞게 생성자를 호출합니다
	
	
	private static final long serialVersionUID = 1L;
	
	private MemberVO member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		
		super(username,password,authorities);
	}
	
	public CustomUser(MemberVO vo) {
		//AuthVO 인스턴스는 GrantedAuthority 객체로 변환해야하므로 stream() 과 map() 을 이용해서 처리합니다.
		super(vo.getUserid(), vo.getUserpw(),vo.getAuthList().stream().map(auth -> 
		new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		
		
		
		this.member = vo;
	}
}

package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;


/*Spring Security

인증 Authentication
: 자신을 증명하는 것, 자기 스스로가 무언가 자신을 증명할 만한 자료를 제시하는 것

권한 Authorization
:권한 부여 ,  남에 의해서 자격이 부여된다는 점


스프링 시큐리티에서 중요한 역활을 하는
인증을 담당하는 AuthenticationManager

ProviderManager는 인증에 대한 처리를 AuthenticationProvider라는 타입의 객체를 이용해서 처리를 위임합니다.
AuthenticationProvider는 실제 인증 작업을 진행합니다 
이때 인증된 정보에는 권한에 대한 정보를 같이 전달하게 되는데 이 처리는 UserDetailService라는 존재와 관련있습니다

UserDetailService라는  인터페이스의 구현체는 실제로 사용자의 정보와 사용자가 가진 권한의 정보를 처리해서 반환하게 됩니다.


일반 시스템에서 userid는 스프링 시큐리티에서는 username에 해당됩니다
User는 인증 정보와 권한을 가진 객체이므로 일반적인 경우에 사용하는 사용자 정보와 다른 의미입니다

개발자가 springsecurity 커스터마이징 하는 방식은 크게 AuthenticationProvider를 직접 구현하는 방식과
실제 처리를 담당하는 UserDeatilService를 구현하는 방식으로 나누어 집니다
대부분 UserDeatilService 구현하는 형태
새로운 프로토콜 인증 방식을 직접구현하는 경우 AuthenticationProvider 인터페이스를 직접 구현해서 사용

*/

@Controller
@Log4j
@RequestMapping("/sample/*")
public class SampleController {

	
	@GetMapping("/all")
	public void doAll() {
		log.info("db all can access everybody!!!!!!!!!!!!!");
	}
	
	@GetMapping("/member")
	public void doMember() {
		log.info(" MEMBER~~~~~~~~~~~~~~~~~");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("ADMIN ~~~~~~~~~~~~~~~~~~~~~~~");
		
	}
}

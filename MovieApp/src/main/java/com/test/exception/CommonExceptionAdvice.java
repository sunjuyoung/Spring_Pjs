package com.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice  //해당객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시
@Log4j
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class) //특정한 타입의 예외를 다루고싶다면 다른 구체적인 예외의 클래스를 지정하면된다
	public String except(Exception ex, Model model) {  //jsp화면에서 구체적인 메시지를 보고싶다면 model이용해서 전달
		
		log.error("exception............"+ ex.getMessage());
		model.addAttribute("exception",ex);
		log.error(model);
		
		return "error_page";
	}
	
	
	//잘못된 URL호출할때 보이는 404에러 , WAS 내부에서 발생하는 에러
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String hadle404(NoHandlerFoundException ex) {
		
		
		return "custom404";
	}
	

}

package com.test.aop;
//log.info등을 이용해서 로그를 기록해 오던 수많은 반복적이면서 핵심로직이 아닌 기능을 관심사로 간주

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	//AspectJ의 표현식 execution의 경우 접근제한자의 특정 클래스의 메서드를 지정할수 있다 
	//맨앞 *는 접근제한자, 맨뒤의 *는 클래스의 이름과 메서드
	@Before("execution(* com.test.service.SampleService*.*(..))")
	public void logBefore() {
		
		log.info("======");
	}
	
	@Before("execution(* com.test.service.SampleService*.doAdd(String,String)) && args(str1,str2)")
	public void logBeforeWithParm(String str1, String str2) {
		
		
		log.info("======");
		log.info(str1);
		log.info(str2);
	}
	
	
	//지정된 대상이 예외를 발생한 후에 동작하면서 문제를 찾을 수 있도록 도와 줄수 있습니다.
	@AfterThrowing(pointcut = "execution(* com.test.service.SampleService*.*(..))",throwing="exception")
	public void logException(Exception exception) {
		
		log.info("exception");
		
	}
	
	@Around("execution(* com.test.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		log.info("target : " + pjp.getTarget());
		log.info("param : " + Arrays.toString(pjp.getArgs()));
		
		
		Object result = null;
		
		try {
			result = pjp.proceed();
			
		}catch(Throwable e) {
			
		}
		
		long end = System.currentTimeMillis();
		
		log.info("time : " + (end - start));
		
		return result;
	}
	
	
}

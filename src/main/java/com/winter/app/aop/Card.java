package com.winter.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect //설정
public class Card {
	
	//advice
//	@Before("execution(* com.winter.app.aop.Transfer.*())")
//	@After("execution(* com.winter.app.aop.Transfer.*())")
	@Around("execution(* com.winter.app.aop.Transfer.*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint)throws Throwable{ //ProceedingJoinPoint point-cut의 메서드를 객체로 만든것
		
		log.info("============================");
		log.info("Card Check 타기");
		
		
	
		
		Object obj = joinPoint.proceed();
		log.info("Card Check 내리기");
		log.info("============================");
		
		return obj;
	}

}

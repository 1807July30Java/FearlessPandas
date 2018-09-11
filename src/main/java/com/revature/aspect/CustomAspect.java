package com.revature.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revature.domain.User;

@Aspect
@Component(value="customAspect")
public class CustomAspect {
	
	
	@Around("customUserAspect(...)")
	public void GetUserInfo(ProceedingJoinPoint pj) throws Throwable {
		User u = (User) pj.getTarget();
		System.out.println("User first name is " + u.getfName());
		pj.proceed();
	}
}
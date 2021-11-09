package com.mycompany.myapp06.common.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.mycompany.myapp06.board.model.vo.Board;
import com.mycompany.myapp06.member.model.vo.Member;

@Service
@Aspect
public class AroundLogAnnotation {
	
	@Pointcut("execution(public * com.mycompany.myapp06..*Service.*(..))")
	public void commonServicePointcut() {
	}
	@Pointcut("execution(public * com.mycompany.myapp06..*Controller.*(..))")
	public void commonControllerPointcut() {
	}
	@Pointcut("execution(public void com.mycompany.myapp06..*Dao.*(..))")
	public void commonDaoReturnPointcut() {
	}
	@Pointcut("execution(public !void com.mycompany.myapp06..*Dao.*(..))")
	public void commonDaoWoReturnPointcut() {
	}
	@Pointcut("execution(public * com.mycompany.myapp06..*Dao.*(..))")
	public void commonDaoPointcut() {
	}
	@Before("commonControllerPointcut()")
	public void beforeLog(JoinPoint jp) {
		System.out.println("beforeLog 메소드!!!"+ jp.getThis() + "target name: "+ jp.getSignature().getName());
		Object[] args = jp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.println("target args" + i + ": "+ args[i].toString());
		}
	}
	
	//@AfterReturning(pointcut="commonControllerPointcut()", returning ="ro")
	public void afterReturningLog(JoinPoint jp, Object ro) {
		System.out.println("afterReturningLog 메소드!!!"+ jp.getThis() + "target name: "+ jp.getSignature().getName());
		if(ro != null) {
			if(ro instanceof Board || ro instanceof Member)
			System.out.println("target return: "+ ro.toString());
		}
	}
	
	@Around("commonDaoPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("aroundLog 메소드!!! "+ pjp.getThis() + " target name: "+ pjp.getSignature().getName());
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object ro = pjp.proceed();   // 타겟 메소드 실행 - 타겟 메소드에 throws가 걸려있을 수 있으므로 이메소드에서도 throws Throwable  작성해주어야함. try/catch 하지말기!
		
		sw.stop();
		System.out.println("메소드 수행시간: "+ sw.getTotalTimeMillis() +"ms");
		if(ro != null) {
			System.out.println("target return: "+ ro.toString());
		}
		return ro;
	}
	
}

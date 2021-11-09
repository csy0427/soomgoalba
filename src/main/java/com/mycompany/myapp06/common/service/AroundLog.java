package com.mycompany.myapp06.common.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundLog {
	
	public void beforeLog(JoinPoint jp) {
		System.out.println("beforeLog 메소드!!!"+ jp.getThis() + "target name: "+ jp.getSignature().getName());
		Object[] args = jp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.println("target args" + i + ": "+ args[i].toString());
		}
	}
	
	public void afterLog(JoinPoint jp) {
		System.out.println("afterLog 메소드!!!"+ jp.getThis() + "target name: "+ jp.getSignature().getName());
	}
	public void afterReturningLog(JoinPoint jp, Object ro) {
		System.out.println("afterReturningLog 메소드!!!"+ jp.getThis() + "target name: "+ jp.getSignature().getName());
		if(ro != null) {
			System.out.println("target return: "+ ro.toString());
		}
	}
	public void afterReturningDaoLog(JoinPoint jp, Object ro) {
		System.out.println("afterReturningLog 메소드!!!"+ jp.getThis() + "target name: "+ jp.getSignature().getName());
		if(ro != null) {
			System.out.println("target return: "+ ro.toString());
		}
	}
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

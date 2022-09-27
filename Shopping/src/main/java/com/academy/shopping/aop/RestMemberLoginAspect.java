package com.academy.shopping.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.category.TopCategoryService;

//횡단적 관심사항에 대한 공통코드를 작성해놓은 객체(하나의 관점으로 둘 예정)
public class RestMemberLoginAspect {
	String tag=this.getClass().getName(); //현재 클래스명이 담아짐
	
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable,MemberException{
		Object returnObj=null;
		System.out.println(tag+" Rest방식 회원 로그인 판단에 관여함");
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = null; 
		for(int i=0; i<args.length; i++) {
			if(args[i] instanceof HttpServletRequest) {
				request = (HttpServletRequest) args[i]; 
				System.out.println("여기 나옴 : " +request);
			}
		}
		HttpSession session =  request.getSession();
		System.out.println(tag+"호출하렬던 메서드 "+joinPoint.getSignature());
		//제외될 명단을 작성하기 위한 uri조사
		String uri = request.getRequestURI();
		ResponseEntity entity = null;
		System.out.println("uri : "+uri);
		if(
				uri.equals("/rest/member/login") ||
				uri.equals("/rest/member/check") ||
				uri.equals("/rest/member") 
		) {
			returnObj = joinPoint.proceed();
			if(returnObj instanceof ResponseEntity) {
				entity=(ResponseEntity) returnObj;
				System.out.println("엔터티 반환"+ entity);
			}
		}else{
			if(session.getAttribute("member")==null) {
				throw new MemberException("회원 로그인이 필요한 서비스 입니다.(rest)");
			}else {
				returnObj = joinPoint.proceed();
				if(returnObj instanceof ResponseEntity) {
					entity=(ResponseEntity) returnObj;
					System.out.println("엔터티 반환"+ entity);
				}
			}
		}
		return returnObj;
	}
}

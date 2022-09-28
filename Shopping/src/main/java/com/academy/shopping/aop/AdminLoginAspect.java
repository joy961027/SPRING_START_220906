package com.academy.shopping.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;

import com.academy.shopping.exception.AdminException;

//관리자모드에서 로그인을 거치지 않고 진행한 요청에 대해선 거부처리
public class AdminLoginAspect {
	
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws AdminException,Throwable {
		//세션을 얻어와서 해당세션에 admin 객체가 들어있는지 판단 및 처리
		Object[] args = joinPoint.getArgs();
		Object returnObj=null; //원래 호출하려던 메서드의 호출 후 반환되는 객체 (string,ModelAndView..)
		HttpServletRequest request = null;
		for(int i=0; i<args.length;i++) {
			if(args[i] instanceof HttpServletRequest) {
				System.out.println("요청객체 발견"+ args);
				request =(HttpServletRequest) args[i];
			}
		}
		HttpSession session = null;
		String uri = request.getRequestURI();
		//로그인이 필요한 서비스와  필요하지 않은 서비스로 크게 나누자
		if(
				uri.equals("/admin/loginForm") ||//로그인 폼
				uri.equals("/admin/registForm") || //회원가입 폼
				uri.equals("/admin/main") ||
				uri.equals("/admin/product/registForm") 
				
				
				) {
			returnObj=joinPoint.proceed();
		}else {
			if(request!=null) {
				session = request.getSession();
				
				if(session.getAttribute("admin")==null) {
					System.out.println("인증되지않은 상태입니다.");
					throw new AdminException("관리자 로그인이 필요한 서비스 입니다.");
				}else {
					//원래하려던 메서드를 대신 호출해주자!
					returnObj = joinPoint.proceed();
				}
			}
		}
		return returnObj;
	}

}

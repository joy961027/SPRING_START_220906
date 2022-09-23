package com.academy.shopping.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.category.TopCategoryService;

//횡단적 관심사항에 대한 공통코드를 작성해놓은 객체(하나의 관점으로 둘 예정)
public class MemberLoginAspect {
	@Autowired
	private TopCategoryService topCategoryService;
	
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws MemberException,Throwable {
		//세션을 끄집어내자
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = null;
		for(int i=0; i<args.length; i++) {
			if(args[i] instanceof HttpServletRequest) {
				request =(HttpServletRequest) args[i];
			}
		}
		
		HttpSession session = null;
		Object returnObj = null;
		String uri = request.getRequestURI();
		
		ModelAndView mav  = null;
		//로그인이 필요한 서비스 와 아닌 서비스 구분
		if(
			uri.equals("/shop/cart/list") 
		) {
			session = request.getSession();
			if(session.getAttribute("member")==null) {
				throw new MemberException("회원 로그인이 필요한 서비스입니다");
			}else {
				returnObj = joinPoint.proceed();
				if(returnObj instanceof ModelAndView) {
					mav = (ModelAndView) returnObj;
				}
			}
		}else {
			returnObj = joinPoint.proceed();
			if(returnObj instanceof ModelAndView) {
				mav = (ModelAndView) returnObj;
			}
		}
		if(mav!=null) {
			List topCategoryList =topCategoryService.selectAll();
			mav.addObject("topCategoryList",topCategoryList);
			returnObj = mav;
		}
		return returnObj;
	}
	

}

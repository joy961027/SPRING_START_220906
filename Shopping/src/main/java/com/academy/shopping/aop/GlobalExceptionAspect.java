package com.academy.shopping.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.exception.EmailException;
import com.academy.shopping.exception.MemberException;
import com.academy.shopping.exception.OrderDetailException;
import com.academy.shopping.exception.OrderSummaryException;

@ControllerAdvice
public class GlobalExceptionAspect {
	
	@ExceptionHandler(AdminException.class)
	public ModelAndView handleException(AdminException e) {
		ModelAndView mav = new ModelAndView("admin/error/result");
		mav.addObject("e",e);
		return mav;
	}
	
	@ExceptionHandler(MemberException.class)
	public ModelAndView handleException(MemberException e) {
		System.out.println("글로벌익셉션");
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e",e);
		return mav;
	}
	
	@ExceptionHandler(OrderSummaryException.class)
	public ModelAndView handleException(OrderSummaryException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e",e);
		return mav;
	}
	
	@ExceptionHandler(OrderDetailException.class)
	public ModelAndView handleException(OrderDetailException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e",e);
		return mav;
	}
	
	@ExceptionHandler(EmailException.class)
	public ModelAndView handleException(EmailException e) {
		ModelAndView mav = new ModelAndView("shop/error/result");
		mav.addObject("e",e);
		return mav;
	}

}

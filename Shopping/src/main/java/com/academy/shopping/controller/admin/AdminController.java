package com.academy.shopping.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	
	//로그인 폼 요청처리
	@GetMapping("/admin/loginForm")
	public ModelAndView getLoginForm(HttpServletRequest request) {
		
		return new ModelAndView("admin/login_Form");
	}
	//관리자 등록 폼 요청 처리
	@GetMapping("/admin/registForm")
	public ModelAndView getRegistForm(HttpServletRequest request) {
		return new ModelAndView("admin/regist");
	}
	
	//관리자 메인 페이지
	@GetMapping("/admin/main")
	public ModelAndView getMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/index");
		return mav;
	}

}

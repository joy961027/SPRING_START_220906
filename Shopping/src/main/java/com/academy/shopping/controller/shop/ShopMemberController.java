package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;
import com.academy.shopping.model.domain.Member;

@Controller
public class ShopMemberController {
	@GetMapping("/shop/member/registForm")
	public ModelAndView getRegistForm(HttpServletRequest request) {
		ModelAndView mav  = new ModelAndView("shop/member/join");
		return mav;
	}


	@GetMapping("/shop/member/loginForm")
	public ModelAndView getLoginForm(HttpServletRequest request) {
		ModelAndView mav  = new ModelAndView("shop/member/login");
		return mav;
	}
	
	@GetMapping("/shop/member/logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		//세션을 무효화시킴 이시점부터는 세션에 담았떤 모든 정보를 참조할 수 없게 된다
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mav  = new ModelAndView("redirect:/shop");
		return mav;
	}
	
	
	
}

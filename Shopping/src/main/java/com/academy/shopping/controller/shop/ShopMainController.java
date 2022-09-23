package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;

//쇼핑몰 메인에 대한 요청처리
@Controller
public class ShopMainController {
	@GetMapping("/shop")
	public ModelAndView getMain(HttpServletRequest request) {
		//카테고리 가져오기
		//신상품 및 각종 괴획 상품등 진열
		
		ModelAndView mav = new ModelAndView("shop/index");
		//아래의 코드는 앞으로 aop를 이용하여 aspect에게 맡김
		//List topCategoryList = topCategoryService.selectAll();
		//mav.addObject("topCategoryList", topCategoryList);
		return mav;
	}
	
	 
	

}

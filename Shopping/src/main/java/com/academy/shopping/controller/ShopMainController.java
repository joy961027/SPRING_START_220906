package com.academy.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;

//쇼핑몰 메인에 대한 요청처리
@Controller
public class ShopMainController {
	@Autowired
	private TopCategoryService topCategoryService;
	
	@GetMapping("/shop")
	public ModelAndView getMain() {
		//카테고리 가져오기
		List topCategoryList = topCategoryService.selectAll();
		//신상품 및 각종 괴획 상품등 진열
		//
		ModelAndView mav = new ModelAndView("shop/index");
		mav.addObject("topCategoryList", topCategoryList);
		return mav;
	}
	
	
	

}

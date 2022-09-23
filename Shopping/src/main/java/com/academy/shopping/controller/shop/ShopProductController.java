package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.product.ProductService;

//쇼핑몰 메인에 대한 요청처리
@Controller
public class ShopProductController {
	@Autowired
	private ProductService productService;
	
	//상품 목록 페이지 요청
	@GetMapping("/shop/product")
	public ModelAndView getproductMain(
			@RequestParam(defaultValue ="0") int topcategory_id,
			@RequestParam(defaultValue ="0") int subcategory_id,
			HttpServletRequest request) {
		//카테고리 가져오기
		System.out.println("이 메서드 호출시 subcategory_id의 값은 " +subcategory_id);
		//선택된 상품 가져오기
		List productList =null;
		if(topcategory_id!=0) {
			productList = productService.selectByTopCategoryId(topcategory_id);
		}else {
			if(subcategory_id==0) {
				productList = productService.selectAll();
			}else {
				productList = productService.selectBySubCategoryId(subcategory_id);
			}
		}
		ModelAndView mav = new ModelAndView("shop/list");
		mav.addObject("productList", productList);
		return mav;
	}
	
	@GetMapping("/shop/product/view")
	public ModelAndView getDetail(int product_id,HttpServletRequest request) {
		//한건 가져오기
		Product product =productService.select(product_id);
		ModelAndView mav = new ModelAndView("shop/detail");
		mav.addObject("product", product);
		return mav;
	}

	
	
}

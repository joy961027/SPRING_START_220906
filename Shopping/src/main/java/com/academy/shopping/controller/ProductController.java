package com.academy.shopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.ProductException;
import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.product.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/admin/product/list")
	public ModelAndView productMain() {
		ModelAndView mav = new ModelAndView("admin/product/main");
		List<Product> productList = productService.selectAll();
		mav.addObject("productList", productList);
		return mav;
	}
	

	@GetMapping("/admin/product/registForm")
	public ModelAndView getRegistForm() {
		
		return new ModelAndView("admin/product/regist");
	}
	
	//상품 등록 요청 처리
	@PostMapping("/admin/product/regist")
	public ModelAndView regist(Product product, HttpServletRequest request) {
		productService.regist(product,request.getServletContext().getRealPath("/resources/data"));
		ModelAndView mav =  new ModelAndView("redirect:/admin/product/list");
		return mav;
	}
	
	@GetMapping("/admin/product/detail")
		public ModelAndView getDetail(int product_id) {
		Product product= productService.select(product_id);
		ModelAndView mav =  new ModelAndView("admin/product/detail");
		mav.addObject("product", product);
		return mav;
	}
	
	
	@ExceptionHandler(ProductException.class)
	public ModelAndView handlerException(ProductException e) {
		ModelAndView mav = new ModelAndView("admin/error/result");
		mav.addObject("e",e);
		return mav;
	}
	
	@ExceptionHandler(UploadException.class)
	public ModelAndView handlerException(UploadException e) {
		ModelAndView mav = new ModelAndView("admin/error/result");
		mav.addObject("e",e);
		return mav;
	}
}
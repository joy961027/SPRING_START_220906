package com.academy.shopping.controller.admin;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.ProductException;
import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.product.ProductService;
import com.academy.shopping.model.util.FileManager;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileManager fileManager;
	
	@GetMapping("/admin/product/list")
	public ModelAndView productMain(HttpServletRequest request) {
		//로그인 인증을 거치지 않았다면, 거부한다.
		HttpSession session = request.getSession();
		ModelAndView mav =null;
		if(session.getAttribute("admin")==null) {
			mav= new ModelAndView("admin/error/auth");
			return mav;
		}
		mav= new ModelAndView("admin/product/main");
		List<Product> productList = productService.selectAll();
		mav.addObject("productList", productList);
		return mav;
	}
	

	@GetMapping("/admin/product/registForm")
	public ModelAndView getRegistForm(HttpServletRequest request) {
		
		return new ModelAndView("admin/product/regist");
	}
	
	//상품 등록 요청 처리
	@PostMapping("/admin/product/regist")
	public ModelAndView regist(Product product, HttpServletRequest request) {
		productService.regist(product,request.getServletContext().getRealPath("/resources/data"));
		ModelAndView mav =  new ModelAndView("redirect:/admin/product/list");
		return mav;
	}
	//관리자 - 상품 detail보기
	@GetMapping("/admin/product/detail")
	public ModelAndView getDetail(int product_id,HttpServletRequest request) {
	Product product= productService.select(product_id);
	ModelAndView mav =  new ModelAndView("admin/product/detail");
	mav.addObject("product", product);
	return mav;
	}
	
	
	
	
	//관리자 -엑셀 등록
	@PostMapping("/admin/product/excel")
	public ModelAndView registByExcel(MultipartFile excel, HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/resources/excel");
		File savedFile = fileManager.saveExcel(path,excel);
		
		//2) 업로드된 엑셀을 대상으로 해석
		productService.registByExcel(savedFile
				,context.getRealPath("/resources/shop/img/product")
				,context.getRealPath("/resources/data"));
		ModelAndView mav = new ModelAndView("redirect:/admin/product/list");
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

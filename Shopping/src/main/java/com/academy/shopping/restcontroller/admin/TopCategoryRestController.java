package com.academy.shopping.restcontroller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.exception.TopCategoryException;
import com.academy.shopping.model.category.TopCategoryService;
import com.academy.shopping.model.domain.TopCategory;

@RestController
public class TopCategoryRestController {
	@Autowired
	private TopCategoryService topCategoryService;
	
	
	//관리자 - 상위 카테고리 등록 요청 처리
	@PostMapping("/admin/topcategory")
	public ResponseEntity regist(TopCategory topCategory) {
		topCategoryService.insert(topCategory);
		ResponseEntity entity = new ResponseEntity(HttpStatus.OK);
		return entity;
	}
	
	
	//관리자 - 상위 카테고리 목록 요청 처리
	@GetMapping("/admin/topcategory")
	public List getList() {
		
		return topCategoryService.selectAll(); //List->json 배열로 변환
	}
	
	@ExceptionHandler(TopCategoryException.class)
	public ResponseEntity handlerException(TopCategoryException e) {
		ResponseEntity entity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}

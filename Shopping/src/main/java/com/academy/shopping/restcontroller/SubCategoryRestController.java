package com.academy.shopping.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.exception.SubCatgoryException;
import com.academy.shopping.model.category.SubCategoryService;
import com.academy.shopping.model.domain.SubCategory;

@RestController
public class SubCategoryRestController {
	@Autowired
	private SubCategoryService subCategoryService;
	
	//관리자 - 해당 상위 카테고리에 소속된 하위 카테고리 목록 가져오기 요청 처리
	@GetMapping("/admin/subcategory/{topcategory_id}")
	public List getSubList(@PathVariable("topcategory_id") int topcategory_id) {
		System.out.println("넘어온 topcategory_id is " + topcategory_id);
		return subCategoryService.selectByTopCategoryId(topcategory_id);
}
	@PostMapping("/admin/subcategory")
	public ResponseEntity regist(SubCategory subCategory) {
		System.out.println(subCategory);
		subCategoryService.insert(subCategory);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html;charset=UTF-8");
		return new ResponseEntity("등록성공",headers,HttpStatus.OK);
	}
	
	@ExceptionHandler(SubCatgoryException.class)
	public ResponseEntity handlerException( SubCatgoryException e) {
		return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}

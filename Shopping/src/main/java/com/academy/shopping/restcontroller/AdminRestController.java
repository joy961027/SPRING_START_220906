package com.academy.shopping.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.model.admin.AdminService;
import com.academy.shopping.model.domain.Admin;

//controller vs restController(자동으로 responsebody가 붙여짐)
@RestController
public class AdminRestController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin")
	@ResponseBody //restcontroller에서는 이속성이 자동부여됨 그리고 이속성의 의미는?//결과를 .jsp를 보내지 않고 데이터만보냄
	public ResponseEntity regist(Admin admin) {
		adminService.insert(admin);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@ExceptionHandler(AdminException.class)
	public String handleException(AdminException e) {
		return e.getMessage();
	}
	
}

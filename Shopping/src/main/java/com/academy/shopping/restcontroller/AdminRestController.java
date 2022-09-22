package com.academy.shopping.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.model.admin.AdminService;
import com.academy.shopping.model.domain.Admin;
import com.academy.shopping.model.util.HashManager;

//controller vs restController(자동으로 responsebody가 붙여짐)
@RestController
public class AdminRestController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private HashManager hashManager;
	
	
	@PostMapping("admin")
	@ResponseBody //restcontroller에서는 이속성이 자동부여됨 그리고 이속성의 의미는?//결과를 .jsp를 보내지 않고 데이터만보냄
	public ResponseEntity regist(Admin admin) {
		adminService.insert(admin);

		//ResponseEntity 응답정보를 전담하는 객체(필수는 아니지만 http 응답정보에 최적화 되어 있어서 편하다
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//로그인 요청 암호화를 시켜야함
	@PostMapping("/admin/login")
	public ResponseEntity<String> login(Admin admin,HttpServletRequest request) {
		//db에 패스워드를 비교하기 전에, 먼저 클라이너트가 전송한 password를 hash 값으로 변경한 후 비교해야함
		String hashedValue = hashManager.getConvertedPassword(admin.getPass()); //평문 -> hash
		admin.setPass(hashedValue); //dto의 패스워드 값을 해쉬 값으로 교체
		
		Admin obj = adminService.selectByIdAndPass(admin); //해당 아이디와 패스워드가 일치하는 회원이 있을대 ,DTO가 널이 아님
		
		System.out.println("로그인 결과 " +obj);
		//세션에 정보를 담아두자!!
		HttpSession session = request.getSession();
		session.setAttribute("admin", obj);
		ResponseEntity<String> entity = new ResponseEntity<String>("1", HttpStatus.OK);
		return  entity;
	}
	
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<String> handleException(AdminException e) {
		ResponseEntity<String> entity = new ResponseEntity<String>("0", HttpStatus.OK);
		return  entity;
	}
	
}

package com.academy.shopping.restcontroller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.member.MemberService;
import com.academy.shopping.model.util.Message;


@RestController
public class ShopMemberRestController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/member")
	public ResponseEntity<Message> regist(HttpServletRequest request,Member member) {
		memberService.insert(member);
		Message message= new Message(1,"가입성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/member/check")
	public ResponseEntity<Message> getId(@RequestParam("customer_id") String customer_id, HttpServletRequest request){
		System.out.println("검증할 아이디는 " +customer_id);
		memberService.selectByCustomerId(customer_id);
		Message message= new Message(1, customer_id + "는 사용가능합니다.");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/member/login")
	public ResponseEntity<Message> login(HttpServletRequest request, Member member){
		HttpSession session=  request.getSession();
		Member result = memberService.selectByIdAndPass(member);
		session.setAttribute("member", result);
		Message message= new Message(1, "로그인에 성공하였습니다.");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK);
		return entity;
		
	}
	
	
//	@ExceptionHandler(MemberException.class)
//	public ResponseEntity handlerException(MemberException e) {
//		Message message= new Message(0,e.getMessage());
//		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK);
//		return entity;
//	}

}

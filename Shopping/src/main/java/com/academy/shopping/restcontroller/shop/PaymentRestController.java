package com.academy.shopping.restcontroller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.model.domain.Cart;
import com.academy.shopping.model.util.Message;


@RestController
public class PaymentRestController {
	
	@PostMapping("/cart")
	public ResponseEntity<Message> registCart(HttpServletRequest request, Cart cart){
		cart.setQuantity(1); //초기에 담을때는 디폴트 1로
		//고객이 넘긴 상품1개를 db에 넣지 않고 메모리에 저장하자
		//이유? db에 넣는건 너무 쉬우니까 session 이용데 대한 훈련 more
		//session 장점: table컬럼에 존재하지 않는 속성도 담을 수 있다 . 즉 자유럽다.
		HttpSession session = request.getSession();
		session.setAttribute(Integer.toString(cart.getProduct_id()), cart); //세션은 map자료형이므로, 순서가 없다 따럿 넣을때 유일한 구분값인 key를 부여하자
		//응답 메시지 생성
		Message message = new Message(Message.SUCCESS, "장바구니에 상품이 담겼습니다.");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK);
		return entity;
	}
	


}

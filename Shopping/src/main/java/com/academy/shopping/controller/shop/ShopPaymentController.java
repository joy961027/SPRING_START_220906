package com.academy.shopping.controller.shop;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.domain.Cart;
import com.academy.shopping.model.domain.Member;

@Controller
public class ShopPaymentController {
	/* 장바구니 목록 요청*/
	@GetMapping("/shop/cart/list")
	public ModelAndView getCartList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shop/payment/cart");
		
		/* 로그인한후 , 장바구니에 물건 담기가 성공했다는 것은 세션에 객체가 담겨져 있다는 의미다..
		 * *따라서 세션에 들어있는 product출력하자
		 */
		HttpSession session = request.getSession();
		//순서가 없는 맵에서객체들을 반복문으로 꺼내는 방법?
		Enumeration<String> en = session.getAttributeNames();
		List cartList = new ArrayList<>();
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			Object obj =  session.getAttribute(key);
			if(obj instanceof Cart) {
				Cart cart = (Cart)obj;
				System.out.println("상품의 이름과 pk는"+cart.getProduct_name()+"," +cart.getProduct_id());
				cartList.add(cart);
			}
		}
		mav.addObject("cartList", cartList);
		return mav;
	}
	
	@PostMapping("/shop/cart/update")
	public ModelAndView update(HttpServletRequest request) {
		String[] product_id = request.getParameterValues("product_id");
		String[] quantity = request.getParameterValues("quantity");
		HttpSession session = request.getSession();
		//순서가 없는 맵에서객체들을 반복문으로 꺼내는 방법?
		for(int i=0; i<product_id.length; i++) {
			System.out.println("수정할 장바구니의 제품은 " +product_id[i] +"이고 그 수량은 " +quantity[i] );
			Cart cart = (Cart) session.getAttribute(product_id[i]);
			cart.setQuantity(Integer.parseInt(quantity[i]));
		}
		ModelAndView mav = new ModelAndView("redirect:/shop/cart/list");
		return mav;
	}
	
	
	
	@GetMapping("/shop/cart/delete")
	public ModelAndView delete(HttpServletRequest request,int product_id) {
		HttpSession session = request.getSession();
		//순서가 없는 맵에서객체들을 반복문으로 꺼내는 방법?
		System.out.println("삭제할 장바구니의 제품은 " +product_id);
		session.removeAttribute(Integer.toString(product_id)); //key값을 이용하여 map에서 제거
		ModelAndView mav = new ModelAndView("redirect:/shop/cart/list");
		return mav;
	}
	
	//결제 페이지 보여주기
	@GetMapping("/shop/checkout")
	public ModelAndView getCheckout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> en= session.getAttributeNames();
		Member member=null;
		List<Cart> cartList = new ArrayList<>();
		while(en.hasMoreElements()) {
			String key = en.nextElement(); //요소 추출
			Object obj = session.getAttribute(key);
			if(obj instanceof Member) { //추출해낸 녀석이 member라면
				member = (Member)obj;
			}else if(obj instanceof Cart){
				cartList.add((Cart)obj);
			}
		}
		//회원정보
		//장바구니 목록
		
		ModelAndView mav = new ModelAndView("shop/payment/checkout");
		mav.addObject("member",member);
		mav.addObject("cartList",cartList);
		return mav;
	}
	
	//결제 확정 요청 처리
	@PostMapping("/shop/pay")
	public ModelAndView pay(HttpServletRequest request) {
		
		//구매자 정보
		
		//배송 정보
		
		//결제 정보
		
		
		return null;
	}

}
















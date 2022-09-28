package com.academy.shopping.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.OrderSummaryException;
import com.academy.shopping.model.domain.OrderSummary;
import com.academy.shopping.model.order.OrderSummaryService;

@Controller
@RequestMapping("/admin")//공통 uri는 밖으로 뺄수 있음
public class OrderController {
	@Autowired
	private OrderSummaryService orderSummaryService;
	
	
	//주문 목록 요청 처리
	@GetMapping("/order/list")
	public ModelAndView getOrderList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/order/order_list");
		List<OrderSummary> orderSummaryList = orderSummaryService.selectAll();
		mav.addObject("orderSummaryList", orderSummaryList);
		return mav;
	}
	
	@GetMapping("/order/detail")
	public ModelAndView getOrderDetail(HttpServletRequest request, int orderSummary_id) {
		ModelAndView mav = new ModelAndView("admin/order/order_detail");
		OrderSummary orderSummary = orderSummaryService.select(orderSummary_id);
		mav.addObject("orderSummary", orderSummary);
		return mav;
	}
}

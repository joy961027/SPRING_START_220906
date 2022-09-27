package com.academy.shopping.model.order;

import java.util.List;

import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderSummary;

public interface OrderSummaryService { //

	public void order(OrderSummary orderSummary); //컨트롤러가 호출 주문요약 주문상세
	
	public List selectAll(); //관리자가 호출
	public OrderSummary select(int ordersummery_id); //주문한건 가져오기
	public List selectByCustomerId(Member member);
	public void update(OrderSummary orderSummary);
	public void delete(OrderSummary orderSummary);

}

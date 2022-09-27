package com.academy.shopping.model.order;

import java.util.List;

import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderSummary;

public interface OrderSummaryDAO {
	public List selectAll();
	public OrderSummary select(int ordersummary_id);
	public List selectByCustomerId(Member member);
	public void insert(OrderSummary orderSummary);
	public void update(OrderSummary orderSummary);
	public void delete(OrderSummary orderSummary);
}

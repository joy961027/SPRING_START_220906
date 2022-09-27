package com.academy.shopping.model.domain;

import java.util.List;

import lombok.Data;
@Data
public class OrderSummary {
	private int ordersummary_id;
	private Member member;
	private Paymethod paymethod;
	private int totalbuy;
	private int totalpay;
	private String buydate;
	private List<OrderDetail> orderDetailList;

}

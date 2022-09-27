package com.academy.shopping.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.OrderSummaryException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderSummary;

@Repository
public class MybatisOrderSummaryDAO implements OrderSummaryDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderSummary select(int ordersummary_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectByCustomerId(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(OrderSummary orderSummary) throws OrderSummaryException {
		int result = sqlSessionTemplate.insert("OrderSummary.insert", orderSummary);
		if(result==0) {
			throw new OrderSummaryException("ordersummary insert  실패");
		}
		
	}

	@Override
	public void update(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

}

package com.academy.shopping.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.OrderDetailException;
import com.academy.shopping.model.domain.OrderDetail;

@Repository
public class MybatisOrderDetailDAO implements OrderDetailDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectByOrderSummaryId(int ordersummery_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(OrderDetail orderDetail) throws OrderDetailException{
		int result = sqlSessionTemplate.insert("OrderDetail.insert", orderDetail);
		if(result==0) {
			throw new OrderDetailException("주문상세정보가 등록되지 못했습니다.");
		}
	}

	@Override
	public void update(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		
	}

}

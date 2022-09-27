package com.academy.shopping.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.shopping.model.domain.Paymethod;
@Service
public class PaymethodServiceImpl implements PaymethodService{
	@Autowired
	private PaymethodDAO payMethodDAO;
	
	@Override
	public List selectAll() {
		return payMethodDAO.selectAll();
	}

	@Override
	public Paymethod select(int paymethod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Paymethod payMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Paymethod payMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Paymethod payMethod) {
		// TODO Auto-generated method stub
		
	}

}

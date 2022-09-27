package com.academy.shopping.model.order;

import java.util.List;

import com.academy.shopping.model.domain.Paymethod;

public interface PaymethodService {
	public List selectAll();
	public Paymethod select(int paymethod_id);
	public void insert(Paymethod payMethod);
	public void update(Paymethod payMethod);
	public void delete(Paymethod payMethod);


}

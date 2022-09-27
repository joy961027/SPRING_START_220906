package com.academy.shopping.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.model.domain.Paymethod;
@Repository
public class MybatisPayMethodDAO implements PaymethodDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List selectAll() {
		
		return sqlSessionTemplate.selectList("Paymethod.selectAll");
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

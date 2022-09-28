package com.academy.shopping.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.ProductException;
import com.academy.shopping.model.domain.Product;

@Repository
public class MybatisProductDAO implements ProductDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Product.selectAll");
		
	}

	@Override
	public List selectBySubCategoryId(int subcategory_id) {
		return sqlSessionTemplate.selectList("Product.selectBySubCategoryId", subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		return sqlSessionTemplate.selectOne("Product.select", product_id);
	}

	@Override
	public void insert(Product product) throws ProductException{
		int result =sqlSessionTemplate.insert("Product.insert", product);
		if(result==0) {
			throw new  ProductException("product insert 실패");
		}
		
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product product) throws ProductException {
		int result = sqlSessionTemplate.delete("Product.delete",product);
		if(result==0) {
			throw new  ProductException("product 삭제 실패");
		}
	}

	
}

package com.academy.shopping.model.category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.SubCatgoryException;
import com.academy.shopping.model.domain.SubCategory;

@Repository
public class MybatisSubCategoryDAO implements  SubCategoryDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("SubCategory.selectAll");
	}

	@Override
	public SubCategory select(int subcategory_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(SubCategory subCategory) throws SubCatgoryException{
		int result = sqlSessionTemplate.insert("SubCategory.insert",subCategory);
		if(result==0) {
			throw new SubCatgoryException("subcategory insert 실패");
		}
	}

	@Override
	public void update(SubCategory subCategory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SubCategory subCategory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List selectByTopCategoryId(int topcategory_id) {
		return sqlSessionTemplate.selectList("SubCategory.selectByTopCategoryId0",topcategory_id);
	}
	
	

}

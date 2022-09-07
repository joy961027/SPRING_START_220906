package com.academy.springmvcsimple.model.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.springmvcsimple.domain.Dept;
import com.academy.springmvcsimple.mybatis.MybatisConfigManager;

@Repository
public class MybatisDeptDAO implements DeptDAO {
	@Autowired
	MybatisConfigManager manager;
	
	@Override
	public int insert(Dept dept) {
		int result=0;
		SqlSession session = manager.getSqlSession();
		result = session.insert("Dept.insert", dept);
		session.commit();
		manager.closeSqlSession(session);
		
		return result;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dept select(int deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Dept dept) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int deptno) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}

package com.academy.springmvcsimple.model.member;
/*
 * 이 DAO는 모든 DEPT관련된 DAO의 최상위 객체이다.
 * 즉 JDBC,Hibernate,Mybatis, JAP...상관없는 최상위 객체
 */

import java.util.List;

import org.springframework.stereotype.Repository;

import com.academy.springmvcsimple.domain.Dept;


public interface DeptDAO { //CRUD
	
	public int insert(Dept dept);
	public List selectAll();
	public Dept select(int deptno);
	public int update(Dept dept);
	public int delete(int deptno);
	
}

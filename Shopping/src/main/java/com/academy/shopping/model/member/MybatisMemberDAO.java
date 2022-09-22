package com.academy.shopping.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.domain.Member;

@Repository
public class MybatisMemberDAO implements MemberDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member selectByCustomerId(String customer_id) throws MemberException{
		Member result = sqlSessionTemplate.selectOne("Member.selectByCustomerId",customer_id);
		if(result!=null) {
			throw new MemberException("중복된 아이디가 있습니다.");
		}
		return result;
	}	
	@Override
	public void insert(Member member) throws MemberException{
		int result = sqlSessionTemplate.insert("Member.insert",member);
		if(result==0) {
			throw new MemberException("member insert 실패");
		}
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) {
		// TODO Auto-generated method stub
		
	}

}

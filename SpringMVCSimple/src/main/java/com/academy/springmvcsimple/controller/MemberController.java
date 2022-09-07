package com.academy.springmvcsimple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springmvcsimple.domain.Emp;
import com.academy.springmvcsimple.model.member.MemberService;

@Controller
public class MemberController {
	String Tag=this.getClass().getName();
	//결합도를 낮추기 위해 
	@Autowired
	private MemberService memberService; //DI를 구현하기 위해 상위 인터페이스를 보유한다.
	
	//사원등록
	@RequestMapping(value="/member/regist", method=RequestMethod.POST)
	public ModelAndView regist(Emp emp) {
		System.out.println(Tag +" : 사원명"+emp.getEname());
		System.out.println(Tag +" : 희망급여"+emp.getSal());
		System.out.println(Tag +" : 부서명"+emp.getDept().getDname());
		memberService.regist(emp); //부서 + 사원 등록 업무가 추상화되어 표현됨.. 즉 service에게 맡김
		
		return new ModelAndView("redirect:/member/list");
	}
	
	//사원 목록 요청 처리
	@RequestMapping(value="/member/list",method =RequestMethod.GET)
	public ModelAndView selectAll() {
		List<Emp> memberList = null;
		memberList = memberService.selectAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberList", memberList);
		mav.setViewName("member/list");
		
		return mav;
		
	}
	
	//사원정보 한건 가져오기 요청 처리
	@RequestMapping(value="/member/detail", method=RequestMethod.GET)
	public ModelAndView select(int empno) {
		Emp emp = null;
		emp = memberService.select(empno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("emp", emp);
		mav.setViewName("member/detail");
		return mav;
	}
	
}

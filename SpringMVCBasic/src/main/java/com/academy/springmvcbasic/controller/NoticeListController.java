package com.academy.springmvcbasic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.model.repository.NoticeDAO;

//공지게시판에 목록 요청을 처리하는 하위 컨트롤러(3단계,4단계)
public class NoticeListController implements Controller {
	private NoticeDAO noticeDAO;
	
	//setter injection(주입)
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계 일시키기
		List boardList= noticeDAO.selectAll();
		System.out.println("1번");
		
		//4단계 view로 전달할 것이 있다면 결과저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.setViewName("board/list");
		System.out.println("2번");
		return mav;
	}

}

package com.academy.springmvcbasic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
//상세보기 요청을 처리하는 하위 컨트롤러

import com.academy.springmvcbasic.domain.Notice;
import com.academy.springmvcbasic.model.repository.NoticeDAO;
public class NoticeContentController implements Controller{
	NoticeDAO noticeDAO;

	
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int notice_id= Integer.parseInt(request.getParameter("notice_id"));
		//3단계
		Notice notice =noticeDAO.select(notice_id);
		
		//4단계 결과 저장
		ModelAndView mav = new ModelAndView("board/content"); //setViewName메서드와 와 동일 
		mav.addObject("notice", notice);
		
		
		return mav;
	}

}

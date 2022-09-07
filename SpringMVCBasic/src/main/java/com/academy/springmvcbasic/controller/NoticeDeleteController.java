package com.academy.springmvcbasic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.model.repository.NoticeDAO;

//삭제요청을 처리하는 하위 컨트롤러
public class NoticeDeleteController implements Controller {
	NoticeDAO noticeDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		
		noticeDAO.delete(notice_id);
		ModelAndView mav = new ModelAndView("redirect:/board/list");
		
		return mav;
	}

}

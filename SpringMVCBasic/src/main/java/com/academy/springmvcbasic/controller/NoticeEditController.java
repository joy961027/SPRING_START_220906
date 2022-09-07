package com.academy.springmvcbasic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.domain.Notice;
import com.academy.springmvcbasic.model.repository.NoticeDAO;

//수정요청을 처리하는 하위 컨트롤러
public class NoticeEditController implements Controller{
	NoticeDAO noticeDAO;
	//new하게 되면 noticeEditController와 noticeDao간 결합도가 강해진다
	//결합도가 강해지면, 만일 noticedao의 클래스가 사라져버리면 di를 이용하여 약화시킬 수 있다.
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice  notice = new Notice();
		notice.setNotice_id(notice_id);
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		noticeDAO.update(notice);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/content?notice_id="+notice_id);
		return mav;
	}

}

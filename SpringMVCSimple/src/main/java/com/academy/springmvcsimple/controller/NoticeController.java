package com.academy.springmvcsimple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springmvcsimple.domain.Notice;
import com.academy.springmvcsimple.model.notice.NoticeDAO;

//이전과는 달리, 하나의 게시판에 사용되는 컨트롤러를 매기능마다 1:1대응하게 클래스를 만들지 말고,
//게시판 1개당 하나의 컨트롤러를 만들자!
//spring의 버전이 올라갈수록 컨트롤러 클래스는 자유도가 높아졌기 때문에, 특정 객체를 상속받거나, 구현해야할 의무가 사라짐
/*이 어노테인션을 선언하는 순간부터 스프링MVC의 각종기능을 사용할 수 있다.
특히 scan의 대상이 될수 잇다. 따라서 xml에 이 컨트롤러를 명시하지 않아도 인스턴스가 올라갈 수 잇다.*/

@Controller
public class NoticeController{
	@Autowired
	private NoticeDAO noticeDAO;
	
	//목록요청 처리 메서드 : 어떤 요청에 대해 이 메서드가 작동할지 매핑을 표현
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String selectAll(Model model) {
		System.out.println("dao의 주소값은"+noticeDAO);
		List boardList =  noticeDAO.selectAll();
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	//글 내용보기 요청 처리메서드
	@RequestMapping(value="/board/content",method=RequestMethod.GET)
	public ModelAndView select(int notice_id) {
		//spring 3.x 부터는 파라미터를 받기 위해 request 객체를 굳이 명시할 필요없이, 파라미터명과 메서드의 매개변수명
		//이 잋리할 경우 자동 매핑이 이루어진다.
		System.out.println("notice_id is "+notice_id);
		//3단계
		Notice notice =noticeDAO.select(notice_id);
		//4단계
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", notice);
		mav.setViewName("board/content");
		return mav;
	}
	
	//삭제 요청 처리 메서드
	@RequestMapping(value="/board/delete" ,method=RequestMethod.GET)
	public String delete(int notice_id) {
		noticeDAO.delete(notice_id);
		return "redirect:/board/list";
	}
	
	
	//글쓰기 요청 처리 메서드
	@RequestMapping(value="/board/regist", method=RequestMethod.POST)
	public String insert(Notice notice) {
		noticeDAO.insert(notice);
		
		return "redirect:/board/list";
	}
	
	
	//수정 요청 처리 메서드
	@RequestMapping(value="/board/edit", method=RequestMethod.POST)
	public ModelAndView edit(Notice notice) {
		noticeDAO.update(notice);
		return new ModelAndView("redirect:/board/content?notice_id="+notice.getNotice_id());
	}
	
	
}

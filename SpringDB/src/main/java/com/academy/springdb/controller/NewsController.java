package com.academy.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.News;
import com.academy.springdb.model.news.NewsService;

@Controller /* 스프링 컨테이너가 메모리에 올릴 대상이 될수 있도록 */
public class NewsController {
	@Autowired
	private NewsService newsSerivce;
	
	
	@GetMapping("/news/list")
	public ModelAndView selectAll() {
		List newsList = newsSerivce.selectAll();
		ModelAndView mav = new ModelAndView("news/list");
		mav.addObject("newsList",newsList);
		
		return mav;
	}
	//글쓰기 요청처리
	@PostMapping("/news/regist")
	public ModelAndView regist(News news){
		newsSerivce.regist(news);
		
		return new ModelAndView("redirect:/news/list");
	}
	//글쓰기 폼 요청
	@GetMapping("/news/registform")
	public ModelAndView registForm() {
		
		return new ModelAndView("news/regist"); 
	}
	@GetMapping("/news/content")
	public ModelAndView select(int news_id) {
		News news =newsSerivce.select(news_id);
		ModelAndView mav = new ModelAndView("news/content");
		mav.addObject("news", news);
		return mav;
	}
	
	
	//스프링 mvc의 컨트롤러의 메서드들 중에서 예외가 발생할때, 이 예외를 처리할 메서드를 지원해준다.
	@ExceptionHandler(NewsException.class )
	public ModelAndView handleException(NewsException e) {
		
		//클라이언트가 에러 메시지를 볼수 있도록 뷰로 저장한다
		ModelAndView mav = new ModelAndView("error/result");
		mav.addObject("msg",e.getMessage());
		
		return mav;
	}
	
	
}

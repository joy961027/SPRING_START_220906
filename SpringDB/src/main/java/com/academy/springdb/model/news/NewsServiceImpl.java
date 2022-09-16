package com.academy.springdb.model.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.springdb.exception.CommentsException;
import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.News;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	@Qualifier("mybatisNewsDAO")
	private NewsDAO newsDAO;
	
	@Autowired
	@Qualifier("mybatisCommentsDAO")
	private CommentsDAO commentsDAO;
	
	@Override
	public List selectAll() {
		return newsDAO.selectAll();
	}

	@Override
	public News select(int news_id) {
		return newsDAO.select(news_id);
	}

	@Override
	public void regist(News news) throws NewsException{
		newsDAO.insert(news);
	}

	@Override
	public void update(News news) throws NewsException {
		newsDAO.update(news);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int news_id) throws NewsException,CommentsException {
		//세부업무 1 자식글 삭제
		commentsDAO.deleteByNewsId(news_id); //예외 걸려있음
		//세부업무 2 부모글 삭제
		newsDAO.delete(news_id); //예외 걸려있음
	}
	
	

}

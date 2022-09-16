package com.academy.springdb.model.news;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.Comments;
import com.academy.springdb.model.domain.News;

@Repository
public class JdbcNewsDAO implements NewsDAO {
	//스프링에서 기존의 jdbc를 보다 편하고, 트랜잭션 등의 처리를 위해 지원되는 객체인 jdbcTemplate을 이용하여
	//쿼리문을 수행
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List selectAll() {
		List<News> list = null; 
		list = jdbcTemplate.query("select * from news order by news_id desc", new RowMapper<News>() {

			@Override
			public News mapRow(ResultSet rs, int rowNum) throws SQLException {
				News news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				//현재 뉴스한거에 딸려있는 자식 목록 가져오기!!
				List<Comments> commentsList = jdbcTemplate.query("select * from comments where news_id = ?", 
						new Object[] {rs.getInt("news_id")} , new RowMapper<Comments>() {

					@Override
					public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
						Comments comments = new Comments();
						comments.setComments_id(rs.getInt("comments_id"));
						comments.setDetail(rs.getString("detail"));
						comments.setAuthor(rs.getString("author"));
						comments.setWritedate(rs.getString("writedate"));
						comments.setNews_id(rs.getInt("news_id"));
						return comments;
					}
				
				
				});
				
				news.setCommentsList(commentsList);
				return news;
			}
		});
		System.out.println("jdbc 목록 결과는 "+list);
		return list;
	}

	@Override
	public News select(int news_id) {
		News news =  jdbcTemplate.queryForObject("select * from news where news_id = ?",new Object[] {news_id}, new RowMapper<News>() {

			@Override
			public News mapRow(ResultSet rs, int rowNum) throws SQLException {
				News news = new News();
				news.setNews_id(news_id);
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				return news;
			}
		});
		return news;
	}

	@Override
	public void insert(News news) throws NewsException{
		System.out.println("jdbc 템플릿으로 insert 시도");
		int result = jdbcTemplate.update("insert into news(news_id,title, writer, content) values(seq_news.nextval,?, ?, ?)" 
				, news.getTitle(),news.getWriter(),news.getContent());
		if(result==0) {
			throw new NewsException("jdbc 템플릿으로 등록 실패");
		}
		
	}

	@Override
	public void update(News news) throws NewsException {
		int result = jdbcTemplate.update("update news set title=?,writer=?,content=? where news_id=?",news.getTitle(),news.getWriter(),news.getContent(),news.getNews_id());
		if(result==0) {
			throw new NewsException("jdbc 템플릿 수정실패");
		}
	}

	@Override
	public void delete(int news_id) throws NewsException {
		int result = jdbcTemplate.update("delete from news where news_id=?", news_id);
		if(result==0) {
			throw new NewsException("jdbc 템플릿 삭제실패");
		}
	}

}

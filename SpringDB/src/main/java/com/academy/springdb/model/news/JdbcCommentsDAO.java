package com.academy.springdb.model.news;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.academy.springdb.exception.CommentsException;
import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.Comments;

@Repository
public class JdbcCommentsDAO implements CommentsDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//이 해당 뉴스기세아 소속된 댓글들 가져오기 (뉴스: 댓글 =1:다)
	@Override
	public List selectByNewsId(int news_id) {
		List<Comments> commentsList = null;
		String sql ="select * from comments where news_id=?";
		commentsList = jdbcTemplate.query(sql, new Object[] {news_id}, new RowMapper<Comments>() {

			@Override
			public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comments comments = new Comments();
				comments.setComments_id(rs.getInt("comments_id"));
				comments.setDetail(rs.getString("detail"));
				comments.setAuthor(rs.getString("author"));
				comments.setWritedate(rs.getString("writedate"));
				comments.setNews_id(news_id);
				
				return comments;
			}
		});
		
		return commentsList;
	}

	@Override
	public Comments select(int comments_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Comments comments) throws CommentsException {
		int result = jdbcTemplate.update("insert into comments(comments_id,detail,author,news_id) values(seq_comments.nextval,?,?,?)",
				comments.getDetail(), comments.getAuthor(), comments.getNews_id());
		if(result==0) {
			throw new CommentsException("jdbc를 통해 insert 실패");
		}
	}

	@Override
	public void update(Comments comments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int comments_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByNewsId(int news_id) {
		// TODO Auto-generated method stub
		
	}

}

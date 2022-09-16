package com.academy.ormsqlmapapp;

import com.academy.ormsqlmapapp.model.board.HibernateBoardDAO;
import com.academy.ormsqlmapapp.model.domain.Board;

public class MainApp {

	public static void main(String[] args) {
		HibernateBoardDAO hibernateBoardDAO = new HibernateBoardDAO();
		Board board = new Board();
		//hibernateBoardDAO.selectAll();
		/*
		board.setTitle("hibernate_title");
		board.setWriter("hibernate_writer");
		board.setContent("hibernate_content");
		hibernateBoardDAO.insert(board);

		board.setBoard_id(25);
		board.setTitle("hibernate");
		board.setWriter("hibernate");
		board.setContent("hibernate");
		hibernateBoardDAO.update(board);
		
		*/
		board.setBoard_id(25);
		hibernateBoardDAO.delete(board);
	}

}

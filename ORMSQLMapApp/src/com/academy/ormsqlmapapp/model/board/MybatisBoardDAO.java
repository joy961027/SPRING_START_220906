package com.academy.ormsqlmapapp.model.board;

import java.util.List;

import com.academy.ormsqlmapapp.model.domain.Board;
import com.academy.ormsqlmapapp.mybatis.ConfigManager;

public class MybatisBoardDAO implements BoardDAO {
	ConfigManager configManager = ConfigManager.getInstance();

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board select(int board_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		
	}

}

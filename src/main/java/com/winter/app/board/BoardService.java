package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardService {
	
		//list
		public List<BoardVO> getList(Pager pager)throws Exception;
		
		//add
		public int add(BoardVO boardVO)throws Exception;
	
		//detail
		public BoardVO getDetail(BoardVO boardVO)throws Exception;
		
		//update
		public int setUpdate(BoardVO boardVO)throws Exception;
		
		//delete
		public int setDelete(BoardVO boardVO)throws Exception;

}

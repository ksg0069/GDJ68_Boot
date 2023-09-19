package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardDAO {
	
	//list
	public List<BoardVO> getList(Pager pager)throws Exception;
	
	//add
	public int add(BoardVO boardVO)throws Exception;
	
	//total
	public Long getCount(Pager pager)throws Exception;
	
	//detail
	public BoardVO getDetail(BoardVO boardVO)throws Exception;
	
	//update
	public int setUpdate(BoardVO boardVO)throws Exception;
	
	//hitUpdate
	public int setHitUpdate(BoardVO boardVO)throws Exception;
	
	//delete
	public int setDelete(BoardVO boardVO)throws Exception;

}



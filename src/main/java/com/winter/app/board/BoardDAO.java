package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardDAO {
	
	//list
	public List<BoardVO> getList(Pager pager)throws Exception;
	
	//add
	public int add(BoardVO boardVO)throws Exception;
	
	public int fileAdd(FileVO fileVO)throws Exception;
	
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

	//fileDown 하기위한 detail가져오기
	public FileVO getFileDetail(FileVO fileVO)throws Exception;
}



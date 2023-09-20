package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.Pager;

public interface BoardService {
	
		//list
		public List<BoardVO> getList(Pager pager)throws Exception;
		
		//add
		public int add(BoardVO boardVO,MultipartFile [] files)throws Exception;
	
		//detail
		public BoardVO getDetail(BoardVO boardVO)throws Exception;
		
		//update
		public int setUpdate(BoardVO boardVO)throws Exception;
		
		//delete
		public int setDelete(BoardVO boardVO)throws Exception;
		
		//fileDown 하기위한 detail가져오기
		public FileVO getFileDetail(FileVO fileVO)throws Exception;

}

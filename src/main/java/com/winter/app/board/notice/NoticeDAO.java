package com.winter.app.board.notice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.winter.app.board.BoardDAO;

//@Repository
@Mapper
public interface NoticeDAO extends BoardDAO{
//mapper의 namespace와 dao의 풀패키지명 일치
//mapper의 id와 dao의 메서드명 일치
}

package com.hanul.gwangs.service;



import com.hanul.gwangs.dto.BoardDTO;
import com.hanul.gwangs.dto.PageRequestDTO;
import com.hanul.gwangs.dto.PageResultDTO;
import com.hanul.gwangs.entity.BoardEntity;

public interface IBoardService {
	
	BoardDTO boardRegister(BoardDTO boardDTO);
	
	//게시판 read 페이지 
	BoardDTO getBoradByBno(Long bno);
	
	// 게시판 update 하기
	BoardDTO updateBoardByBno(Long bno , BoardDTO boardDTO);
	
	// 게시판 bno로 삭제하기
	void deleteBoard(Long bno);
	
	PageResultDTO<BoardDTO, BoardEntity> getList(PageRequestDTO pageRequestDTO);

}

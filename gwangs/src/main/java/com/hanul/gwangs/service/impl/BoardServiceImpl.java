package com.hanul.gwangs.service.impl;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hanul.gwangs.dto.BoardDTO;
import com.hanul.gwangs.dto.PageRequestDTO;
import com.hanul.gwangs.dto.PageResultDTO;
import com.hanul.gwangs.entity.BoardEntity;
import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.repository.BoardRepository;
import com.hanul.gwangs.repository.MemberRepository;
import com.hanul.gwangs.service.IBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements IBoardService{

	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	
	@Override
	public BoardDTO boardRegister(BoardDTO boardDTO) {
		MemberEntity memberEntity = memberRepository.findById(boardDTO.getMemberid()).orElseThrow();
		
		BoardEntity boardEntity = BoardEntity.builder()
											.bno(boardDTO.getBno())
											.title(boardDTO.getTitle())
											.content(boardDTO.getContent())
											.member(memberEntity)
											.build();
		
		BoardEntity saveEntity = boardRepository.save(boardEntity);
		
		return BoardDTO.builder()
					.bno(saveEntity.getBno())
					.title(saveEntity.getTitle())
					.content(saveEntity.getContent())
					.memberid(memberEntity.getId())
					.build();
	}



	@Override
	public BoardDTO getBoradByBno(Long bno) {
		BoardEntity entity = boardRepository.findById(bno).orElseThrow();
		
		return BoardDTO.builder()
						.bno(entity.getBno())
						.title(entity.getTitle())
						.content(entity.getContent())
						.user_nickname(entity.getMember().getUserNickname())
						.regDate(entity.getRegDate())
						.build();
	}

	@Override
	public BoardDTO updateBoardByBno(Long bno, BoardDTO boardDTO) {
		BoardEntity entity = boardRepository.findById(bno).orElseThrow();
		
		entity.setTitle(boardDTO.getTitle());
		entity.setContent(boardDTO.getContent());
		
		BoardEntity updateEntity = boardRepository.save(entity);
		
		return BoardDTO.builder()
						.bno(updateEntity.getBno())
						.title(updateEntity.getTitle())
						.content(updateEntity.getContent())
						.regDate(updateEntity.getRegDate())
						.modDate(updateEntity.getModDate())
						.build();
	}

	@Override
	public void deleteBoard(Long bno) {
		boardRepository.findById(bno).orElseThrow();
		boardRepository.deleteById(bno);
	}

	@Override
	public PageResultDTO<BoardDTO, BoardEntity> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());
		
		Page<BoardEntity> result = boardRepository.findAll(pageable);
		
		Function<BoardEntity, BoardDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<>(result, fn);
	}
	
	private BoardDTO entityToDto(BoardEntity boardEntity) {
		return BoardDTO.builder()
				.bno(boardEntity.getBno())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContent())
				.user_nickname(boardEntity.getMember().getUserNickname())
				.regDate(boardEntity.getRegDate())
				.modDate(boardEntity.getModDate())
				.build();
	}

	
	
}

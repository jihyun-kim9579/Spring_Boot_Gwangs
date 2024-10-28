package com.hanul.gwangs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hanul.gwangs.dto.ReserveDTO;
import com.hanul.gwangs.entity.FieldEntity;
import com.hanul.gwangs.entity.MemberEntity;
import com.hanul.gwangs.entity.ReserveEntity;
import com.hanul.gwangs.repository.FieldRepository;
import com.hanul.gwangs.repository.MemberRepository;
import com.hanul.gwangs.repository.ReserveRepository;
import com.hanul.gwangs.service.IReserveService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
@RequiredArgsConstructor
public class ReserveServiceImpl implements IReserveService{
	
	private final ReserveRepository reserveRepository;
	private final MemberRepository memberRepository;
	private final FieldRepository fieldRepository;
	
	@Override
	public ReserveEntity reserveConfirm(ReserveDTO reserveDTO) {
		MemberEntity memberEntity = memberRepository.findById(reserveDTO.getMemberId()).orElseThrow();
		FieldEntity fieldEntity = fieldRepository.findById(reserveDTO.getFieldId()).orElseThrow();
		
		 ReserveEntity entity = ReserveEntity.builder()
	                .rId(reserveDTO.getRId())
	                .reserveName(reserveDTO.getReserveName())
	                .reserveNumber(reserveDTO.getReserveNumber())
	                .reserveDate(reserveDTO.getReserveDate())
	                .reserveTime(reserveDTO.getReserveTime())
	                .reserveAddr(reserveDTO.getReserveAddr())
	                .reserveCost(reserveDTO.getReserveCost())
	                .member(memberEntity)    
	                .field(fieldEntity)      
	                .build();
		
		return reserveRepository.save(entity);
	}

	@Override
	public List<ReserveDTO> findReserveByUserId(Long m_id) {
		
		
		return reserveRepository.findByMemberId(m_id)
								.stream()
								.map(entity -> ReserveDTO.builder()
										.rId(entity.getRId())
										.reserveName(entity.getReserveName())
										.reserveNumber(entity.getReserveNumber())
										.reserveAddr(entity.getReserveAddr())
										.reserveDate(entity.getReserveDate())
										.reserveTime(entity.getReserveTime())
										.memberId(m_id)
										.build())
								.collect(Collectors.toList());
	}

}

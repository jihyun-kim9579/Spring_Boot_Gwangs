package com.hanul.gwangs.service;


import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.hanul.gwangs.dto.FieldDTO;
import com.hanul.gwangs.entity.FieldEntity;

public interface IFieldService {
	// 매니저가 구장 등록
	FieldDTO fieldRegister(FieldDTO fieldDTO);
	
	// 유저들이 모든 구장을 보기 위함
	Page<FieldDTO> findAllFields(int page , int size);
	
	// 구장 상세에서 id를 기준으로 정보를 가져오기 위함
	FieldDTO fidnById(Long id);
	
	// 구장 중에서 , 예약이 되면 fstatus 를 false 로 바꾸는 
	void deactivateField(Long fId);
	
	// 특정 날짜의 구장 검색
	Page<FieldDTO> findFieldsByDate(int page, int size , LocalDate date);

	
	
	
	default FieldEntity dtoToEntity(FieldDTO fieldDTO) {
		
		FieldEntity entity = FieldEntity.builder()
									.fId(fieldDTO.getFId())
									.fieldName(fieldDTO.getFieldName())
									.fieldNumber(fieldDTO.getFieldNumber())
									.fieldDate(fieldDTO.getFieldDate())
									.fieldTime(fieldDTO.getFieldTime())
									.fieldAddr(fieldDTO.getFieldAddr())
									.fieldCost(fieldDTO.getFieldCost())
									.build();
		return entity;
	}
	
	default FieldDTO entityToDto(FieldEntity fieldEntity) {
		FieldDTO dto = FieldDTO.builder()
							.fId(fieldEntity.getFId())
							.fieldName(fieldEntity.getFieldName())
							.fieldNumber(fieldEntity.getFieldNumber())
							.fieldDate(fieldEntity.getFieldDate())
							.fieldTime(fieldEntity.getFieldTime())
							.fieldAddr(fieldEntity.getFieldAddr())
							.fieldCost(fieldEntity.getFieldCost())
							.build();
		return dto;
	}
}

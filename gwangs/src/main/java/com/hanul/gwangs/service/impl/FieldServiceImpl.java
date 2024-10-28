package com.hanul.gwangs.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanul.gwangs.dto.FieldDTO;
import com.hanul.gwangs.entity.FieldEntity;
import com.hanul.gwangs.repository.FieldRepository;
import com.hanul.gwangs.service.IFieldService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
@RequiredArgsConstructor
public class FieldServiceImpl implements IFieldService{

	private final FieldRepository fieldRepository;
	
	@Override
	public FieldDTO fieldRegister(FieldDTO fieldDTO) {
		FieldEntity entity = dtoToEntity(fieldDTO);
		log.info("fieldEntity : {}" , entity);
		
		FieldEntity returnEntity = fieldRepository.save(entity);
		
		return entityToDto(returnEntity);
	}

	@Override
	public Page<FieldDTO> findAllFields(int page , int size) {
		
		Sort sort = Sort.by(
				Sort.Order.asc("fieldDate"),
				Sort.Order.asc("fieldTime"),
				Sort.Order.asc("fieldNumber")
			
			);
		
		Pageable pageable = PageRequest.of(page -1, size , sort);
		log.info("Requested page: {}, size: {}", page, size);
		
		Page<FieldEntity> entity = fieldRepository.findAllByFstatusTrue(pageable);
		
		return entity.map(this::entityToDto);
	}

	@Override
	public FieldDTO fidnById(Long id) {
		FieldEntity entity = fieldRepository.findById(id).orElseThrow();
		
		return entityToDto(entity);
	}
	
	@Transactional
	@Override
	public void deactivateField(Long fId) {
		FieldEntity entity = fieldRepository.findById(fId).orElseThrow();
		entity.setFstatus(false);
		fieldRepository.save(entity);
	}
	
	

}

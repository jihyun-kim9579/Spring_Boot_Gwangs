package com.hanul.gwangs.service;



import java.util.List;

import com.hanul.gwangs.dto.ReserveDTO;
import com.hanul.gwangs.entity.ReserveEntity;


public interface IReserveService {
	
	ReserveEntity reserveConfirm(ReserveDTO reserveDTO);
	
	List<ReserveDTO> findReserveByUserId(Long m_id);
	
	
}

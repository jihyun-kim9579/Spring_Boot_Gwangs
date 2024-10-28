package com.hanul.gwangs.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveDTO {
	
	private Long rId;
	private String reserveName;
	private int    reserveNumber;
	private LocalDate reserveDate;
	private String reserveTime;
	private String reserveAddr;
	private String reserveCost;
	
	private Long memberId;
	private Long fieldId;
}

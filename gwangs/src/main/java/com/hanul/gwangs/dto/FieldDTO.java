package com.hanul.gwangs.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO {
	
	
	private Long fId;
	private String fieldName;
	private int    fieldNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fieldDate;
	
	private String fieldTime;
	private String fieldAddr;
	private String fieldCost;
}

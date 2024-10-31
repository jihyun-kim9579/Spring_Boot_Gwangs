package com.hanul.gwangs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDTO {
	private Long cId;
	private String title;
	private String startDate;
	private String endDate;
	private String userId;
}

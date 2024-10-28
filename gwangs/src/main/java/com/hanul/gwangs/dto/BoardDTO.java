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
public class BoardDTO {
	
	private Long bno;
	private String title;
	private String content;
	private Long memberid;
	private String user_nickname;
	private LocalDate regDate;
	private LocalDate modDate;
	
}

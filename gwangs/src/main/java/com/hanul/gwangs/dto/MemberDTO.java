package com.hanul.gwangs.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	
	private Long id;
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_nickname;
	private String user_phone;
	private String user_email;
	
	@Builder.Default
	private boolean mstatus = true;
}

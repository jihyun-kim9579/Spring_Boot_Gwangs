package com.hanul.gwangs.dto;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.hanul.gwangs.entity.MemberRole;

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
	private String new_password;
	private String current_password;
	private LocalDate regDate;
	private LocalDate modDate;
	
	@Builder.Default
	private boolean mstatus = true;
	
	@Builder.Default
	private Set<MemberRole> roleSet = new HashSet<>();
}

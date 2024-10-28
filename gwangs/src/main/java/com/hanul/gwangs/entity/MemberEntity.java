package com.hanul.gwangs.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@SequenceGenerator(name = "MEMBER_SEQ_GEN",
					sequenceName = "member_seq",
					initialValue = 1,
					allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "MEMBER_SEQ_GEN")
	private Long id;
	
	@Column(name = "USER_ID" , unique = true)
	private String userId;
	private String user_pwd;
	private String user_name;
	private String user_nickname;
	private String user_phone;
	private String user_email;
	
	@Builder.Default
	private boolean mstatus = true;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private Set<MemberRole> roleSet = new HashSet<>();
	
	public void addMemberRole(MemberRole memberRole) {
		roleSet.add(memberRole);
	}
	
}

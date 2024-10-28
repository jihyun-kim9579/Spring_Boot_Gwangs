package com.hanul.gwangs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@SequenceGenerator(name = "BOARD_SEQ_GEN",
					sequenceName = "board_seq",
					initialValue = 1,
					allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "BOARD_SEQ_GEN")
	private Long bno;
	
	private String title;
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private MemberEntity member;
}

package com.hanul.gwangs.entity;

import java.time.LocalDate;

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
@SequenceGenerator(name = "RESERVE_SEQ_GEN",
					sequenceName = "reserve_seq",
					initialValue = 1,
					allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReserveEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "RESERVE_SEQ_GEN")
	private Long rId;
	
	private String reserveName;
	private int    reserveNumber;
	private LocalDate reserveDate;
	private String reserveTime;
	private String reserveAddr;
	private String reserveCost;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private MemberEntity member;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")  
    private FieldEntity field;
}




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
@SequenceGenerator(name = "CALENDAR_SEQ_GEN",
					sequenceName = "calendar_seq",
					initialValue = 1,
					allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CalendarEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "CALENDAR_SEQ_GEN")
	private Long cId;
	
	
	private String title;
	private String startDate;
	private String endDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private MemberEntity member;
}

package com.hanul.gwangs.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
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
@SequenceGenerator(name = "FIELD_SEQ_GEN",
					sequenceName = "field_seq",
					initialValue = 1,
					allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FieldEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "FIELD_SEQ_GEN")
	private Long fId;
	
	private String fieldName;
	private int    fieldNumber;
	private LocalDate fieldDate;
	private String fieldTime;
	private String fieldAddr;
	private String fieldCost;
	
	@Builder.Default
	private boolean fstatus = true;
	
}



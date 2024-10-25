package com.hanul.gwangs.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

//다른 엔티티 클래스에서 상속받아 사용할 수 있는 기본 속성을 제공함을 명시
@MappedSuperclass
// Entity class Lifecycle event 설정
// 엔티티가 수정되거나 생성될 때 value 값이 동작하여 필드를 자동으로 업데이트
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Setter
public class BaseEntity {
	
	@CreatedDate
	@Column(name = "regdate", updatable = false, nullable = false)
	private LocalDate regDate;
	
	@LastModifiedDate
	@Column(name = "moddate")
	private LocalDate modDate;
	
}

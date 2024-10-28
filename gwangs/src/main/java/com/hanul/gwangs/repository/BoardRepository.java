package com.hanul.gwangs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanul.gwangs.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

}

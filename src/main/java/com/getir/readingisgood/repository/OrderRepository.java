package com.getir.readingisgood.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getir.readingisgood.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

	Page<OrderEntity> findByUserId(Long userId, Pageable pageable);
	
	Optional<List<OrderEntity>> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
	
	Optional<List<OrderEntity>> findByUserId(Long userId);
}

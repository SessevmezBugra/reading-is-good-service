package com.getir.readingisgood.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.getir.readingisgood.dto.CreateOrderDto;
import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.dto.PageDto;
import com.getir.readingisgood.entity.OrderEntity;

public interface OrderService {

	public PageDto<OrderDto> findByUserId(Long userId, Pageable pageable);
	
	public void createOrder(CreateOrderDto createOrderDto);
	
	public OrderDto findById(Long orderId);
	
	public List<OrderDto> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
	
	public List<OrderEntity> findByUserId(Long userId);
}

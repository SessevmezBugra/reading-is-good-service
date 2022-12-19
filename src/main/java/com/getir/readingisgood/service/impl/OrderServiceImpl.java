package com.getir.readingisgood.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.getir.readingisgood.dto.CreateOrderDto;
import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.dto.PageDto;
import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.entity.OrderItemEntity;
import com.getir.readingisgood.entity.ProductEntity;
import com.getir.readingisgood.entity.UserEntity;
import com.getir.readingisgood.exception.ReadingIsGoodException;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.service.ProductService;
import com.getir.readingisgood.service.UserService;
import com.getir.readingisgood.util.AuthUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;
	private final AuthUtil authUtil;
	private final UserService userService;
	private final ProductService productService;

	@Override
	public PageDto<OrderDto> findByUserId(Long userId, Pageable pageable) {
		Page<OrderEntity> page = orderRepository.findByUserId(userId, pageable);
		return new PageDto<OrderDto>(page, Arrays.asList(modelMapper.map(page.getContent(), OrderDto[].class)));
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	@Override
	public void createOrder(CreateOrderDto createOrderDto) {
		UserEntity foundUser = userService.findByEmail(authUtil.getUsername());
		
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUser(foundUser);

		List<OrderItemEntity> orderItemEntities = convertOrderItemEntitiesAndSubstractStock(createOrderDto, orderEntity);
		
		BigDecimal orderTotal = orderItemEntities.stream().map(OrderItemEntity::getTotal).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		
		orderEntity.setItems(orderItemEntities);
		orderEntity.setTotal(orderTotal);
		orderRepository.save(orderEntity);
	}

	private List<OrderItemEntity> convertOrderItemEntitiesAndSubstractStock(CreateOrderDto createOrderDto, OrderEntity orderEntity) {
		return createOrderDto.getItems().stream().map((itemDto) -> {
			ProductEntity foundProduct = productService.findById(itemDto.getProductId());
			productService.subtractStock(foundProduct.getId(), itemDto.getQuantity());
			BigDecimal orderItemTotal = itemDto.getQuantity().multiply(foundProduct.getPrice());
			OrderItemEntity itemEntity = new OrderItemEntity();
			itemEntity.setOrder(orderEntity);
			itemEntity.setProduct(foundProduct);
			itemEntity.setQuantity(itemDto.getQuantity());
			itemEntity.setTotal(orderItemTotal);
			return itemEntity;
		}).collect(Collectors.toList());
	}

	@Override
	public OrderDto findById(Long orderId) {
		OrderEntity foundOrderEntity = orderRepository.findById(orderId).orElseThrow(() -> new ReadingIsGoodException("Order not found"));
		return modelMapper.map(foundOrderEntity, OrderDto.class);
	}

	@Override
	public List<OrderDto> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		List<OrderEntity> orderEntities = orderRepository.findByCreatedDateBetween(startDate, endDate).orElse(Collections.emptyList());
		return Arrays.asList(modelMapper.map(orderEntities, OrderDto[].class));
	}

	@Override
	public List<OrderEntity> findByUserId(Long userId) {
		return orderRepository.findByUserId(userId).orElse(Collections.emptyList());
	}

}

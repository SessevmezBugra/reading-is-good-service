package com.getir.readingisgood.service.impl;

import java.math.BigDecimal;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.getir.readingisgood.dto.MonthlyOrderStatisticDto;
import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.entity.OrderItemEntity;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.service.StatisticService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
	
	private final OrderService orderService;
	
	@Override
	public List<MonthlyOrderStatisticDto> findMontlyOrderStatisticByUserId(Long userId) {
		List<OrderEntity> orderEntities = orderService.findByUserId(userId);
		Map<Month, MonthlyOrderStatisticDto> statisticMap = new HashMap<Month, MonthlyOrderStatisticDto>();
		
		List<MonthlyOrderStatisticDto> orderStatisticDtos = orderEntities.stream().map((orderEntity) -> {
			BigDecimal productQuantity = orderEntity.getItems().stream().map(OrderItemEntity::getQuantity).reduce(BigDecimal.ZERO,
				BigDecimal::add);
			Month orderMonth = orderEntity.getCreatedDate().getMonth();
			MonthlyOrderStatisticDto statisticDto = statisticMap.get(orderMonth);
			if(ObjectUtils.isEmpty(statisticDto))
				statisticDto = new MonthlyOrderStatisticDto();
			statisticDto.setMonth(orderMonth);
			statisticDto.setTotalOrderCount(statisticDto.getTotalOrderCount().add(BigDecimal.valueOf(1)));
			statisticDto.setTotalPrice(statisticDto.getTotalPrice().add(orderEntity.getTotal()));
			statisticDto.setTotalBookCount(productQuantity);
			statisticMap.put(orderMonth, statisticDto);
			return statisticDto;
		}).collect(Collectors.toList());
		
		return orderStatisticDtos;
	}

	
}

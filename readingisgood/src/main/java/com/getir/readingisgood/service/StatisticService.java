package com.getir.readingisgood.service;

import java.util.List;

import com.getir.readingisgood.dto.MonthlyOrderStatisticDto;

public interface StatisticService {

	List<MonthlyOrderStatisticDto> findMontlyOrderStatisticByUserId(Long userId);
}

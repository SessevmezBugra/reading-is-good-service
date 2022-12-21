package com.getir.readingisgood.dto;

import java.math.BigDecimal;
import java.time.Month;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyOrderStatisticDto {

	private Month month;
	private BigDecimal totalOrderCount;
	private BigDecimal totalBookCount;
	private BigDecimal totalPrice;
	
}

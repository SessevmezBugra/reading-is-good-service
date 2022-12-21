package com.getir.readingisgood.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderItemDto {

	private Long productId;
	private BigDecimal quantity;
}

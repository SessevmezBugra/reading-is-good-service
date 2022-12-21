package com.getir.readingisgood.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.common.RestResponse;
import com.getir.readingisgood.constant.ApiPath;
import com.getir.readingisgood.dto.CreateOrderDto;
import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.OrderCtrl.CTRL)
@RequiredArgsConstructor
public class OrderRestController {

	private final OrderService orderService;
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestResponse<Void>> createOrder(@RequestBody CreateOrderDto createOrderDto) {
		orderService.createOrder(createOrderDto);
        return ResponseEntity.ok(RestResponse.empty());
    }
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<RestResponse<OrderDto>> getOrderByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(RestResponse.of(orderService.findById(orderId)));
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<RestResponse<List<OrderDto>>> getOrdersByStartAndEndDate(
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(RestResponse.of(orderService.findByStartDateAndEndDate(startDate, endDate)));
    }
}

package com.getir.readingisgood.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.common.RestResponse;
import com.getir.readingisgood.constant.ApiPath;
import com.getir.readingisgood.dto.CreateProductDto;
import com.getir.readingisgood.dto.ProductDto;
import com.getir.readingisgood.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.BookCtrl.CTRL)
@RequiredArgsConstructor
public class BookRestController {

	private final ProductService productService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestResponse<Void>> createBook(@RequestBody CreateProductDto createProductDto) {
		productService.createProduct(createProductDto);
        return ResponseEntity.ok(RestResponse.empty());
    }
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<RestResponse<List<ProductDto>>> getAll() {
        return ResponseEntity.ok(RestResponse.of(productService.findAll()));
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{productId}/add", method = RequestMethod.PUT)
    public ResponseEntity<RestResponse<Void>> addStock(@PathVariable Long productId, @RequestParam BigDecimal stock) {
		productService.addStock(productId, stock);
		return ResponseEntity.ok(RestResponse.empty());
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{productId}/substract", method = RequestMethod.PUT)
    public ResponseEntity<RestResponse<Void>> substractStock(@PathVariable Long productId, @RequestParam BigDecimal stock) {
		productService.subtractStock(productId, stock);
		return ResponseEntity.ok(RestResponse.empty());
    }
}

package com.getir.readingisgood.service;

import java.math.BigDecimal;
import java.util.List;

import com.getir.readingisgood.dto.ProductDto;
import com.getir.readingisgood.entity.ProductEntity;

public interface ProductService {

	public void createProduct(ProductDto productDto);
	
	public void addStock(Long productId, BigDecimal stock);
	
	public void subtractStock(Long productId, BigDecimal stock);
	
	public List<ProductDto> findAll();
	
	public ProductEntity findById(Long id);
}

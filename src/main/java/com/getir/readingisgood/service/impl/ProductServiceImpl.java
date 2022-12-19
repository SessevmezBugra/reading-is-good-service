package com.getir.readingisgood.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.getir.readingisgood.dto.ProductDto;
import com.getir.readingisgood.entity.ProductEntity;
import com.getir.readingisgood.exception.ReadingIsGoodException;
import com.getir.readingisgood.repository.ProductRepository;
import com.getir.readingisgood.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public void createProduct(ProductDto productDto) {
		ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
		productRepository.save(productEntity);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	@Override
	public void addStock(Long productId, BigDecimal stock) {
		ProductEntity productEntity = findById(productId);
		productEntity.setStock(productEntity.getStock().add(stock));
		productRepository.save(productEntity);
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	@Override
	public void subtractStock(Long productId, BigDecimal stock) {
		ProductEntity productEntity = findById(productId);
		if(productEntity.getStock().compareTo(BigDecimal.valueOf(0)) == 0 ||  stock.compareTo(productEntity.getStock()) == 1)
			throw new ReadingIsGoodException("Stock is not enough.");
		productEntity.setStock(productEntity.getStock().subtract(stock));
		productRepository.save(productEntity);
	}
	
	@Override
	public List<ProductDto> findAll() {
		List<ProductEntity> productEntities = productRepository.findAll();
		return Arrays.asList(modelMapper.map(productEntities, ProductDto[].class));
	}
	
	@Override
	public ProductEntity findById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ReadingIsGoodException("Product not found."));
	}

}

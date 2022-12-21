package com.getir.readingisgood.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity extends Auditable<String> {

	@Id
	@Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="QUANTITY")
	private BigDecimal quantity;
	
	@Column(name="TOTAL")
	private BigDecimal total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private OrderEntity order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private ProductEntity product;
	
}

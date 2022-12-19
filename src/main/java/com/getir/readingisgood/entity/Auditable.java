package com.getir.readingisgood.entity;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable<T> {

	@CreatedDate
	@Column(name = "CREATED_DATE")
	protected LocalDate createdDate;
	
	@CreatedBy
	@Column(name = "CREATED_BY")
	protected T createdBy;
	
	@LastModifiedDate
	@Column(name = "UPDATED_ON")
	protected LocalDate updatedOn;
	
	@LastModifiedBy
	@Column(name = "UPDATED_BY")
	protected T updatedBy;
	
}
package com.getir.readingisgood.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class PageDto<T> {

	private int number;
    private int size;
    private Sort sort;
    private int totalPages;
    private Long totalElements;
    private List<T> content;
    
    public PageDto(Page<?> page, List<T> list) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.sort = page.getSort();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.content = list;
    }
}

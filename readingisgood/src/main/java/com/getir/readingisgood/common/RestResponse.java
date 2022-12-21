package com.getir.readingisgood.common;

import lombok.Data;

@Data
public class RestResponse<T> {

	private T data;
	private String uiMessage;
	
	public RestResponse(T data) {
		this.data = data;
	}
	
	public static <T> RestResponse<T> of(T t) {
		return new RestResponse<>(t);
	}
	
	public static RestResponse<Void> empty() {
		return new RestResponse<>(null);
	}
}

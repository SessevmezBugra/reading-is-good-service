package com.getir.readingisgood.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestUser {
	
	TestUser1("testuser1@getir.com", "testuser1"),
	TestUser2("testuser2@getir.com", "testuser2"),
	TestUser3("testuser3@getir.com", "testuser3"),
	TestUser4("testuser4@getir.com", "testuser4"),
	TestUser5("testuser5@getir.com", "testuser5");
	
	private String username;
	private String password;
}

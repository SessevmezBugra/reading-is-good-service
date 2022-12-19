package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.RegistrationDto;
import com.getir.readingisgood.entity.UserEntity;

public interface UserService {

	public void register(RegistrationDto registrationDto);
	
	public UserEntity findByEmail(String email);
}

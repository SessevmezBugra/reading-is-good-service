package com.getir.readingisgood.config.security;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.getir.readingisgood.entity.AuthorityEntity;
import com.getir.readingisgood.entity.UserEntity;
import com.getir.readingisgood.enums.ReadingIsGoodRole;
import com.getir.readingisgood.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitAdminData {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@PostConstruct
    public void init() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("admin@getir.com");
        userEntity.setPassword(passwordEncoder.encode("admin"));
        
        if(userRepository.findByEmail(userEntity.getEmail()).isPresent())
        	return;
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setUser(userEntity);
        authorityEntity.setRole(ReadingIsGoodRole.ADMIN);
        
        List<AuthorityEntity> authorityEntities = new LinkedList<>();
        authorityEntities.add(authorityEntity);
        
        userEntity.setAuthorities(authorityEntities);
        
        userRepository.save(userEntity);
    }
}

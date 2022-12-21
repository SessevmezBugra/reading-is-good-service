package com.getir.readingisgood.config.security;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private Environment environment;
	
	@PostConstruct
    public void init() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(environment.getProperty("admin.username"));
        userEntity.setPassword(passwordEncoder.encode(environment.getProperty("admin.password")));
        
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
	
	public static void main(String[] args) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.err.println(encoder.encode("testuser1"));
		System.err.println(encoder.encode("testuser2"));
		System.err.println(encoder.encode("testuser3"));
		System.err.println(encoder.encode("testuser4"));
		System.err.println(encoder.encode("testuser5"));
	}
}

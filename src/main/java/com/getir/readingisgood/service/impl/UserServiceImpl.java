package com.getir.readingisgood.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.getir.readingisgood.dto.RegistrationDto;
import com.getir.readingisgood.entity.AuthorityEntity;
import com.getir.readingisgood.entity.UserEntity;
import com.getir.readingisgood.enums.ReadingIsGoodRole;
import com.getir.readingisgood.exception.ReadingIsGoodException;
import com.getir.readingisgood.repository.UserRepository;
import com.getir.readingisgood.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService{

	private final UserRepository userRepository;
    private final PasswordEncoder paswordEncoder;

	
    @Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity foundUser = findByEmail(username);
		List<SimpleGrantedAuthority> authorities = foundUser.getAuthorities().stream().map((auth) -> new SimpleGrantedAuthority("ROLE_" + auth.getRole())).collect(Collectors.toList());
        return new User(foundUser.getEmail(), foundUser.getPassword(), authorities);

	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public void register(RegistrationDto registrationDto) {
		Optional<UserEntity> userOpt = userRepository.findByEmail(registrationDto.getEmail());
		if(userOpt.isPresent())
			throw new ReadingIsGoodException("User already exist.");
		
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(registrationDto.getEmail());
		userEntity.setPassword(paswordEncoder.encode(registrationDto.getPassword()));
		
		AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setUser(userEntity);
        authorityEntity.setRole(ReadingIsGoodRole.CUSTOMER);
        
        List<AuthorityEntity> authorityEntities = new LinkedList<>();
        authorityEntities.add(authorityEntity);
        userEntity.setAuthorities(authorityEntities);
        
		userRepository.save(userEntity);
	}

	@Override
	public UserEntity findByEmail(String email) {
		Optional<UserEntity> userOpt = userRepository.findByEmail(email);
		return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid email."));
	}

}

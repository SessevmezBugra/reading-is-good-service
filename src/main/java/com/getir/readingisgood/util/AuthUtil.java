package com.getir.readingisgood.util;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthUtil {
	
	private final AuthenticationManager authenticationManager;

	public String getUsername() {
		if(ObjectUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication()))
			return "SYSTEM";
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
	
	public void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}

package com.getir.readingisgood.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.getir.readingisgood.util.AuthUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditAwareConfig implements AuditorAware<String> {
	
	private final AuthUtil authUtil;

	@Override
	public Optional<String> getCurrentAuditor() {
		String currentPrincipalName = authUtil.getUsername();
		return Optional.ofNullable(currentPrincipalName);
	}
}
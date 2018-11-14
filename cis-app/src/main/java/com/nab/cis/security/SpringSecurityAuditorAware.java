package com.nab.cis.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.nab.cis.constant.ApplicationConstants;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
	
	@Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(ApplicationConstants.DEFAULT_SYSTEM_ACCOUNT);
    }

}

package com.example.tracking_order.config;

import com.example.tracking_order.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class JpaAudittingConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserEntity) {
                    return Optional.of(((UserEntity) principal).getFullname());
                }
            }
            // Nếu chưa login thì trả về "system"
            return Optional.of("system");
        };
    }


}

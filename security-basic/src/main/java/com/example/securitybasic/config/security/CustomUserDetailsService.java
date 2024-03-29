package com.example.securitybasic.config.security;

import com.example.securitybasic.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return applicationUserRepository.findApplicationUserByLogin(username)
                .map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException(
                        "User with username = '%s' not found".formatted(username)
                ));
    }
}

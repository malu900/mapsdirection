package com.location.maps.service;

import com.location.maps.model.User;
import com.location.maps.respository.UserRepository;
import com.location.maps.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// UserDetailsService which loads a user’s data given its username
@Service
public interface CustomUserDetailsService extends UserDetailsService {

    @Override
    @Transactional
    UserDetails loadUserByUsername(String usernameOrEmail);

    // This method is used by JWTAuthenticationFilter
    @Transactional
    UserDetails loadUserById(Long id);
}
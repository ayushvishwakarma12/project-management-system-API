package com.example.projectmanagementsystem.project_management_system.config;

import com.example.projectmanagementsystem.project_management_system.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private UserJpaRepository userJpaRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.projectmanagementsystem.project_management_system.model.User user = userJpaRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }
}

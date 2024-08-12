package com.example.projectmanagementsystem.project_management_system.config;


import com.example.projectmanagementsystem.project_management_system.model.JwtRequest;
import com.example.projectmanagementsystem.project_management_system.model.JwtResponse;
import com.example.projectmanagementsystem.project_management_system.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        logger.info("requeset: " + request.getPassword().length());
        logger.info("Attempting to authenticate user: {}", request.getUsername(), request.getPassword());
        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder().jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void doAuthenticate(String username, String password) {
        logger.info("username password: " + username + password);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        logger.info("authentication: " + authentication);

        try {
           Authentication auth =  manager.authenticate(authentication);
            logger.info("Authentication successful for user: {}", username);

        } catch (BadCredentialsException e) {
            logger.error("Invalid username or password for user: {}", username);
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> exceptionHandler(BadCredentialsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid Username or Password");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}

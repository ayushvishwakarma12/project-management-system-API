package com.example.projectmanagementsystem.project_management_system.security;

import com.example.projectmanagementsystem.project_management_system.config.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        logger.info("Header: {}", requestHeader);

        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            token = requestHeader.substring(7);
            logger.info("token {} :", token);
            try {
                username = jwtHelper.getUsernameFromToken(token);
                logger.info("Username {} :", username);
            } catch (IllegalArgumentException e) {
                logger.error("Illegal argument while fetching the username!", e);
            } catch (ExpiredJwtException e) {
                logger.error("JWT token is expired!", e);
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token!", e);
//            } catch (SignatureException e) {
//                logger.error("JWT signature does not match locally computed signature!", e);
            } catch (Exception e) {
                logger.error("Error while parsing JWT token!", e);
            }
        } else {
            logger.info("Invalid Header Value!");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (jwtHelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.info("JWT token validation failed!");
            }
        }

        filterChain.doFilter(request, response);
    }
}

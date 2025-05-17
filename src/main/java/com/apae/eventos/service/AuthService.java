package com.apae.eventos.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.apae.eventos.configuration.JwtProperties;
import com.apae.eventos.dtos.requests.AuthenticationRequest;
import com.apae.eventos.dtos.responses.AuthenticationResponse;
import com.apae.eventos.configuration.service.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Tentando autenticar usuário: {}", request.email());
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            )
        );
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(userDetails);
        
        log.info("Usuário autenticado com sucesso: {}", request.email());
        
        return new AuthenticationResponse(
            jwt,
            jwtProperties.getTokenPrefix().trim()
        );
    }
}
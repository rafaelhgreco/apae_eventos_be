package com.apae.eventos.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apae.eventos.dtos.responses.UserDTO;
import com.apae.eventos.entity.UserEntity;
import com.apae.eventos.mapper.UserMapper;
import com.apae.eventos.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserDTO createUser(String nome, String email, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(nome);
        userEntity.setEmail(email);
        String hashedPassword = passwordEncoder.encode(password);
        userEntity.setPassword(hashedPassword);
        
        return UserMapper.toDTO(userRepository.save(userEntity));
    }
    
    public boolean verificarSenha(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
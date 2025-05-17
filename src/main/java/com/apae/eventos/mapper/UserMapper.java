package com.apae.eventos.mapper;

import com.apae.eventos.dtos.responses.UserDTO;
import com.apae.eventos.entity.UserEntity;

public class UserMapper {
    public static UserDTO toDTO(UserEntity userEntity) {
        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }

    public static UserEntity toEntity(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.id());
        userEntity.setName(dto.nome());
        userEntity.setEmail(dto.email());
        return userEntity;
    }
}

package com.apae.eventos.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
    @NotBlank(message = "Nome é obrigatório")
    String name,
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,
    @NotBlank(message = "Senha é obrigatória")
    String password
) {}

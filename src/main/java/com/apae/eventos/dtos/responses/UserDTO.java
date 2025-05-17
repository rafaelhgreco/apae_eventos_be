package com.apae.eventos.dtos.responses;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserDTO(
    Long id, 
    @NotBlank(message = "Nome é obrigatório")
    String nome,
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email
) {}

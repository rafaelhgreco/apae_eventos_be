package com.apae.eventos.dtos.responses;

public record AuthenticationResponse(
    String token,
    String type
) {}

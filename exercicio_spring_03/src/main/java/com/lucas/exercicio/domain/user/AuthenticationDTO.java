package com.lucas.exercicio.domain.user;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotNull
        String username,
        @NotNull
        String password
) {
}

package com.lucas.exercicio.domain.user;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        UserRoles role
) {
}

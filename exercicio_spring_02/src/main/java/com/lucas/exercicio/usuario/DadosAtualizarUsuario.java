package com.lucas.exercicio.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarUsuario(
        String nome,
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Por favor, digite um e-mail com caracteres válidos.")
        String email
) {
}

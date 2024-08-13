package com.lucas.exercicio.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @Valid
        @NotBlank(message = "O nome não pode estar vazio ou nulo.")
        String nome,
        @Valid
        @NotBlank(message = "O e-mail não pode estar vazio ou nulo.")
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Por favor, digite um e-mail com caracteres válidos.")
        String email
) {
}

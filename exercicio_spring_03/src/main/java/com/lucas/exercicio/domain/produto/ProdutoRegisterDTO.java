package com.lucas.exercicio.domain.produto;

import jakarta.validation.constraints.NotNull;

public record ProdutoRegisterDTO(
        @NotNull
        String nome,
        double preco
) {
}

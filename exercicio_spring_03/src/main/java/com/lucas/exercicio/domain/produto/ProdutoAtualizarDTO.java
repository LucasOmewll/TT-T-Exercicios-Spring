package com.lucas.exercicio.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoAtualizarDTO(
        String nome,
        @PositiveOrZero
        double preco
) {
}

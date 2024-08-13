package com.spring.exercicio.tarefa;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTarefa(
        @NotNull
        Long id,
        String descricao,
        boolean completa
) {
}

package com.spring.exercicio.tarefa;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroTarefa(
        @NotNull
        String descricao,
        @NotNull
        boolean completa
) {
}

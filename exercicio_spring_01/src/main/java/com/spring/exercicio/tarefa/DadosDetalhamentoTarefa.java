package com.spring.exercicio.tarefa;

public record DadosDetalhamentoTarefa(
        Long id,
        String descricao,
        boolean completa
) {
    public DadosDetalhamentoTarefa(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getDescricao(), tarefa.isCompleta());
    }
}

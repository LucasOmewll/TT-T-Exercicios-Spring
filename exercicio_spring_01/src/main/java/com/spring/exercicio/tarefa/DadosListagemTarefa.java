package com.spring.exercicio.tarefa;

public record DadosListagemTarefa(
        String descricao,
        boolean completa
) {
    public DadosListagemTarefa(Tarefa tarefa){
        this(tarefa.getDescricao(), tarefa.isCompleta());
    }
}

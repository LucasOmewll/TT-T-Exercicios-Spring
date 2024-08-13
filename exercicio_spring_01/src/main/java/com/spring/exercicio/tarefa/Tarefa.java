package com.spring.exercicio.tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Tarefa")
@Table(name = "tarefas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tarefa {
    public Tarefa(DadosCadastroTarefa dados){
        this.descricao = dados.descricao();
        this.completa = dados.completa();
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String descricao;
    boolean completa;

    public void atualizarInformacoes(DadosAtualizarTarefa dados) {
        if (dados.descricao() != null){
            this.descricao = dados.descricao();
        }

        if(this.completa != dados.completa()){
            this.completa = dados.completa();
        }
    }

    public void alterarCompleta() {
        this.completa = !this.completa;
    }
}

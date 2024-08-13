package com.lucas.exercicio.domain.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;

    public Produto(ProdutoRegisterDTO dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
    }

    public void atualizar(ProdutoAtualizarDTO dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.preco() != this.preco && dados.preco() != 0.0D){
            this.preco = dados.preco();
        }
    }
}

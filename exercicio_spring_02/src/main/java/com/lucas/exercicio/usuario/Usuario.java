package com.lucas.exercicio.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    public Usuario(DadosCadastroUsuario dados){
        this.nome = dados.nome();
        this.email = dados.email();
    }

    public void atualizar(DadosAtualizarUsuario dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.email() != null){
            this.email = dados.email();
        }
    }
}

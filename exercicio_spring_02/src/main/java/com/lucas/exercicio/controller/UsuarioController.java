package com.lucas.exercicio.controller;

import com.lucas.exercicio.repositories.UsuarioRepository;
import com.lucas.exercicio.usuario.DadosAtualizarUsuario;
import com.lucas.exercicio.usuario.DadosCadastroUsuario;
import com.lucas.exercicio.usuario.DadosListagemUsuario;
import com.lucas.exercicio.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios", produces = {"application/json"})
@Tag(name = "usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @Operation(summary = "Retorna uma lista dos usuários cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários obtida com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a obtenção da lista de usuários")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> buscar() {
        var lista = repository.findAll().stream().map(DadosListagemUsuario::new).toList();

        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Realiza o cadastro de um usuário.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro do usuário")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemUsuario> adicionar(@Valid @RequestBody DadosCadastroUsuario dados) {
        var usuario = new Usuario(dados);

        repository.save(usuario);

        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @Operation(summary = "Atualiza as informações de um usuário.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações atualizadas com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar informações do usuário")
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemUsuario> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizarUsuario dados){
        var usuario = repository.getReferenceById(id);

        usuario.atualizar(dados);

        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @Operation(summary = "Realiza a exclusão de um usuário.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a exclusão do usuário")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

package com.spring.exercicio.controllers;

import com.spring.exercicio.repositories.TarefaRepository;
import com.spring.exercicio.tarefa.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/tarefas", produces = {"application/json"})
@Tag(name = "tarefas")
public class TarefaController {
    @Autowired
    private TarefaRepository repository;

    @Operation(summary = "Realiza o cadastro de uma tarefa.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa cadastrada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro da tarefa")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTarefa> cadastrar(@RequestBody @Valid DadosCadastroTarefa dados, UriComponentsBuilder uriBuilder){
        var tarefa = new Tarefa(dados);

        repository.save(tarefa);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Retorna uma lista das tarefas cadastradas.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas obtida com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a obtenção da lista de tarefas")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemTarefa>> listar() {

        var lista = repository.findAll().stream().map(DadosListagemTarefa::new).toList();

        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Retorna uma tarefa.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Tarefa obtida com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a obtenção da tarefa")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTarefa> detalhar(@PathVariable Long id){
        var tarefa = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoTarefa(tarefa));
    }

    @Operation(summary = "Atualiza as informações de uma tarefa.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a obtenção da tarefa")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTarefa> atualizar(@RequestBody @Valid DadosAtualizarTarefa dados){
        var tarefa = repository.getReferenceById(dados.id());

        tarefa.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTarefa(tarefa));
    }

    @Operation(summary = "Altera o estado de conclusão da tarefa.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a obtenção da tarefa")
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTarefa> completar(@PathVariable Long id){
        var tarefa = repository.getReferenceById(id);

        tarefa.alterarCompleta();

        return ResponseEntity.ok(new DadosDetalhamentoTarefa(tarefa));
    }

    @Operation(summary = "Realiza a exclusão de uma tarefa.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a obtenção da tarefa")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity<?> deletar(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
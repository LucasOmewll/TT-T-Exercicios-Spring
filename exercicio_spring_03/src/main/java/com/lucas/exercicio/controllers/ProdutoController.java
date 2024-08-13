package com.lucas.exercicio.controllers;

import com.lucas.exercicio.domain.produto.Produto;
import com.lucas.exercicio.domain.produto.ProdutoAtualizarDTO;
import com.lucas.exercicio.domain.produto.ProdutoListagemDTO;
import com.lucas.exercicio.domain.produto.ProdutoRegisterDTO;
import com.lucas.exercicio.repositories.ProdutoRepository;
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
@RequestMapping(value = "/produtos", produces = {"application/json"})
@Tag(name = "produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @Operation(summary = "Cadastra um produto.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "403", description = "O usuário não possui permissão para realizar a requisição"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro do produto")
    })
    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<Void> registrar(@RequestBody @Valid ProdutoRegisterDTO dados){
        var produto = new Produto(dados);

        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Busca uma lista dos produtos cadastrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos adquirida com sucesso"),
            @ApiResponse(responseCode = "403", description = "O usuário não possui permissão para realizar a requisição"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar lista de produtos")
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoListagemDTO>> buscarTodosProdutos(){
        var lista = produtoRepository.findAll().stream().map(ProdutoListagemDTO::new).toList();

        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Busca um produto a partir de seu ID.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto buscado com sucesso"),
            @ApiResponse(responseCode = "403", description = "O usuário não possui permissão para realizar a requisição"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar produto")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProdutoListagemDTO> buscarProduto(@PathVariable Long id){
        var produto = produtoRepository.getReferenceById(id);

        return ResponseEntity.ok(new ProdutoListagemDTO(produto));
    }

    @Operation(summary = "Atualiza um produto a partir de seu ID.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto buscado com sucesso"),
            @ApiResponse(responseCode = "403", description = "O usuário não possui permissão para realizar a requisição"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar produto")
    })
    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<ProdutoListagemDTO> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoAtualizarDTO dados){
        var produto = produtoRepository.getReferenceById(id);

        produto.atualizar(dados);

        return ResponseEntity.ok(new ProdutoListagemDTO(produto));
    }

    @Operation(summary = "Exclui um produto a partir de seu ID.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "403", description = "O usuário não possui permissão para realizar a requisição"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao excluir produto")
    })
    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var produto = produtoRepository.getReferenceById(id);

        produtoRepository.delete(produto);

        return ResponseEntity.noContent().build();
    }
}

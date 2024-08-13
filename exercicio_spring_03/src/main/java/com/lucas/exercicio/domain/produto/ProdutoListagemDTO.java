package com.lucas.exercicio.domain.produto;

public record ProdutoListagemDTO(
        Long id,
        String nome,
        double preco
) {
    public ProdutoListagemDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco());
    }
}

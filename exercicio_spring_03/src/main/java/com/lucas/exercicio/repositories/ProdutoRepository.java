package com.lucas.exercicio.repositories;

import com.lucas.exercicio.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

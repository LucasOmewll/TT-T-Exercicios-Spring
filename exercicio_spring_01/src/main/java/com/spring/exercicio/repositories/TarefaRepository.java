package com.spring.exercicio.repositories;

import com.spring.exercicio.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}

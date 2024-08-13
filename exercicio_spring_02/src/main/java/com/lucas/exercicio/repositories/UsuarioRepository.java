package com.lucas.exercicio.repositories;

import com.lucas.exercicio.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

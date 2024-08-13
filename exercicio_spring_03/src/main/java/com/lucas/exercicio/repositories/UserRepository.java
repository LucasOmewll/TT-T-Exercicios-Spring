package com.lucas.exercicio.repositories;

import com.lucas.exercicio.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
     UserDetails findByUsername(String username);
}

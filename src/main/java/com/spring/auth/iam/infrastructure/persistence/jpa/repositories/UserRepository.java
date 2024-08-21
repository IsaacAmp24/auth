package com.spring.auth.iam.infrastructure.persistence.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.auth.iam.domain.model.aggregates.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // busca en la base de datos un usuario por su nombre de usuario
    Optional<User> findByUsername(String username);
    
    // verifica si existe un usuario con el nombre de usuario
    boolean existsByUsername(String username);
}

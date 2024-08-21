package com.spring.auth.iam.infrastructure.persistence.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.auth.iam.domain.model.entities.Role;
import com.spring.auth.iam.domain.model.valueobjects.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // busca en la base de datos un rol por su nombre
    Optional<Role> findByName(Roles name);

    // verifica si existe un rol con el nombre
    boolean existsByName(Roles name);

}

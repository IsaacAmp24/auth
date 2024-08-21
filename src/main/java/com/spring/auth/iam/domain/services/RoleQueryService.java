package com.spring.auth.iam.domain.services;

import java.util.List;
import java.util.Optional;

import com.spring.auth.iam.domain.model.entities.Role;
import com.spring.auth.iam.domain.model.queries.GetAllRolesQuery;
import com.spring.auth.iam.domain.model.queries.GetRoleByNameQuery;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query); // retorna todos los roles
    Optional<Role> handle(GetRoleByNameQuery query); // retorna un rol por nombre

}

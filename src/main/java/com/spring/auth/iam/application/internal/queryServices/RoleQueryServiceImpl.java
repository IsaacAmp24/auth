package com.spring.auth.iam.application.internal.queryServices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.auth.iam.domain.model.entities.Role;
import com.spring.auth.iam.domain.model.queries.GetAllRolesQuery;
import com.spring.auth.iam.domain.model.queries.GetRoleByNameQuery;
import com.spring.auth.iam.domain.services.RoleQueryService;
import com.spring.auth.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.roleName());
    }

}

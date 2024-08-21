package com.spring.auth.iam.application.internal.commandServices;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.spring.auth.iam.domain.model.commands.SeedRolesCommand;
import com.spring.auth.iam.domain.model.entities.Role;
import com.spring.auth.iam.domain.model.valueobjects.Roles;
import com.spring.auth.iam.domain.services.RoleCommandService;
import com.spring.auth.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }

}

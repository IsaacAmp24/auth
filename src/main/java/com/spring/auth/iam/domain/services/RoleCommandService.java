package com.spring.auth.iam.domain.services;

import com.spring.auth.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}

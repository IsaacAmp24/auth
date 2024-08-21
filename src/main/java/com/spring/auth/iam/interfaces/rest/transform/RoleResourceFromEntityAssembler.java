package com.spring.auth.iam.interfaces.rest.transform;

import com.spring.auth.iam.domain.model.entities.Role;
import com.spring.auth.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toRoleResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }

}

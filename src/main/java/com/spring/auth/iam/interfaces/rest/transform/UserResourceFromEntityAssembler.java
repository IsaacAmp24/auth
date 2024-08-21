package com.spring.auth.iam.interfaces.rest.transform;

import com.spring.auth.iam.domain.model.aggregates.User;
import com.spring.auth.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toUserResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(role -> role.getStringName()).toList();

        return new UserResource(
            entity.getId(),
            entity.getUsername(),
            roles
        );
    }

}

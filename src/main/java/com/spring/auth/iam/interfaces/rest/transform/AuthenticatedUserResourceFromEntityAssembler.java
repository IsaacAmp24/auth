package com.spring.auth.iam.interfaces.rest.transform;

import com.spring.auth.iam.domain.model.aggregates.User;
import com.spring.auth.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(
            user.getId(),
            user.getUsername(),
            token
        );
    }

}

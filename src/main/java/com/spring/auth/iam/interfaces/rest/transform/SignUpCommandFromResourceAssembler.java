package com.spring.auth.iam.interfaces.rest.transform;

import java.util.ArrayList;

import com.spring.auth.iam.domain.model.commands.SignUpCommand;
import com.spring.auth.iam.domain.model.entities.Role;
import com.spring.auth.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null
                ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList()
                : new ArrayList<Role>();
        System.out.print("Roles:");
        
        System.out.println(!roles.isEmpty() ? roles.getFirst().getName().name() : "No roles");
        return new SignUpCommand(resource.username(), resource.password(), roles);
    }

}
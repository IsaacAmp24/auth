package com.spring.auth.iam.interfaces.rest.transform;

import com.spring.auth.iam.domain.model.commands.SignInCommand;
import com.spring.auth.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }

}

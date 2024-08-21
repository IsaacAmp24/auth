package com.spring.auth.iam.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.auth.iam.domain.services.UserCommandService;
import com.spring.auth.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.spring.auth.iam.interfaces.rest.resources.SignInResource;
import com.spring.auth.iam.interfaces.rest.resources.SignUpResource;
import com.spring.auth.iam.interfaces.rest.resources.UserResource;
import com.spring.auth.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.spring.auth.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.spring.auth.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.spring.auth.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value="/api/v1/auth", produces= MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);

        if(authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toUserResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    

}
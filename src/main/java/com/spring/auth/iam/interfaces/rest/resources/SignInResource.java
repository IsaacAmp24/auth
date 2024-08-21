package com.spring.auth.iam.interfaces.rest.resources;

public record SignInResource(
    String username,
    String password
) {

}

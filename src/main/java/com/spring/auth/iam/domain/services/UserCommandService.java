package com.spring.auth.iam.domain.services;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.spring.auth.iam.domain.model.aggregates.User;
import com.spring.auth.iam.domain.model.commands.SignInCommand;
import com.spring.auth.iam.domain.model.commands.SignUpCommand;


public interface UserCommandService {

    Optional<User> handle(SignUpCommand command); // comando para registrar un usuario

    Optional<ImmutablePair<User, String>> handle(SignInCommand command); // comando para iniciar sesión, devuelve un par de usuario y token
}
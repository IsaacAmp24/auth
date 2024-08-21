package com.spring.auth.iam.application.internal.commandServices;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import com.spring.auth.iam.application.internal.outboundservices.hashing.HashingService;
import com.spring.auth.iam.application.internal.outboundservices.tokens.TokenService;
import com.spring.auth.iam.domain.model.aggregates.User;
import com.spring.auth.iam.domain.model.commands.SignInCommand;
import com.spring.auth.iam.domain.model.commands.SignUpCommand;
import com.spring.auth.iam.domain.model.valueobjects.Roles;
import com.spring.auth.iam.domain.services.UserCommandService;
import com.spring.auth.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.spring.auth.iam.infrastructure.persistence.jpa.repositories.UserRepository;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }



    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");

        var roles = command.roles();
        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.ESTUDIANTE);
            if (role.isPresent()) roles.add(role.get());
        } else roles = roles.stream().map(role -> roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role not found"))).toList();
            var user = new User(command.username(), hashingService.encode(command.password()), roles);
            userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(command.password(), user.getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.getUsername());
        return Optional.of(new ImmutablePair<>(user, token));
    }

}

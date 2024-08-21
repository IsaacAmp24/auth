package com.spring.auth.iam.application.internal.queryServices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.auth.iam.domain.model.aggregates.User;
import com.spring.auth.iam.domain.model.queries.GetAllUsersQuery;
import com.spring.auth.iam.domain.model.queries.GetUserByIdQuery;
import com.spring.auth.iam.domain.model.queries.GetUserByUsernameQuery;
import com.spring.auth.iam.domain.services.UserQueryService;
import com.spring.auth.iam.infrastructure.persistence.jpa.repositories.UserRepository;

@Service
public class UserQueryServiceImpl implements UserQueryService{

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }

}

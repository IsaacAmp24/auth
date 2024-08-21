package com.spring.auth.iam.domain.services;

import java.util.List;
import java.util.Optional;

import com.spring.auth.iam.domain.model.aggregates.User;
import com.spring.auth.iam.domain.model.queries.GetAllUsersQuery;
import com.spring.auth.iam.domain.model.queries.GetUserByIdQuery;
import com.spring.auth.iam.domain.model.queries.GetUserByUsernameQuery;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query); // retorna todos los usuarios
    Optional<User> handle(GetUserByIdQuery query); // retorna un usuario por id
    Optional<User> handle(GetUserByUsernameQuery query); // retorna un usuario por username


}

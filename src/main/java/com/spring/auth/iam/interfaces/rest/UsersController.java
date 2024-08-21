package com.spring.auth.iam.interfaces.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.auth.iam.domain.model.queries.GetAllUsersQuery;
import com.spring.auth.iam.domain.model.queries.GetUserByIdQuery;
import com.spring.auth.iam.domain.services.UserQueryService;
import com.spring.auth.iam.interfaces.rest.resources.UserResource;
import com.spring.auth.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping(value = "/api/v1/users", produces= MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Users API")
public class UsersController {

    private final UserQueryService userQueryService;

    public UsersController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toUserResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping(value= "/{userId}")
    public ResponseEntity<UserResource> getUserById(@RequestParam Long userId) {
        var GetUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(GetUserByIdQuery);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var userResource = UserResourceFromEntityAssembler.toUserResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
        
    }
    
    

}

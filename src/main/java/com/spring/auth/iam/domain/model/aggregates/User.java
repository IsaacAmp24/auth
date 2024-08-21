package com.spring.auth.iam.domain.model.aggregates;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spring.auth.iam.domain.model.entities.Role;
import com.spring.auth.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Getter
public class User extends AuditableAbstractAggregateRoot<User> {

    @Column(nullable = false, unique = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 100)
    private String password;

    @Getter
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "users_roles",
    joinColumns= @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    
    private Set<Role> roles;

    public User() {
        this.roles = new HashSet<>();
    }

    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public User(String usename, String password, List<Role> roles) {
        this(usename, password);
        addRoles(roles);
    }

    public final User addRoles(Role roles) {
        this.roles.add(roles);
        return this;
    }
    
    public final User addRoles(List<Role> roles) {
        var validateRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validateRoleSet);
        return this;
    }

}
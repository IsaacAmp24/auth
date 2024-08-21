package com.spring.auth.shared.domain.model.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditableModel {
    @Getter
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Date createdAt;

    @Getter
    @LastModifiedDate
    @JsonIgnore
    @Column(nullable = false)
    private Date updatedAt;
}

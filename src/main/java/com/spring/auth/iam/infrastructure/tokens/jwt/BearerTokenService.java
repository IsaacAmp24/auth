package com.spring.auth.iam.infrastructure.tokens.jwt;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;

import com.spring.auth.iam.application.internal.outboundservices.tokens.TokenService;

import jakarta.servlet.http.HttpServletRequest;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest request);
    String generateToken(Authentication authentication);

}

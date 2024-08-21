package com.spring.auth.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.auth.iam.application.internal.outboundservices.hashing.HashingService;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}

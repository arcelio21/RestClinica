package com.example.exception.user;

import org.springframework.security.core.AuthenticationException;

public class UsernameInvalid extends AuthenticationException {

    public UsernameInvalid(String msg) {
        super(msg);
    }
}

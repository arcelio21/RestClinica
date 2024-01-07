package com.example.dto.security;

import org.springframework.http.HttpMethod;

public record UrlIgnore(
        String path,
        HttpMethod[] typeRequest
) {
}

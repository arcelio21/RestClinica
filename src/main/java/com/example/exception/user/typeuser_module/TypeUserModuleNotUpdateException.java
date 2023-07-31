package com.example.exception.user.typeuser_module;

import lombok.Getter;

@Getter
public class TypeUserModuleNotUpdateException extends RuntimeException{

    private final Object data;

    public TypeUserModuleNotUpdateException(String message, Object data) {
        super(message);
        this.data = data;
    }

}

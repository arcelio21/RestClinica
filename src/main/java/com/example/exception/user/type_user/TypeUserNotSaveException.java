package com.example.exception.user.type_user;

public class TypeUserNotSaveException extends RuntimeException{

    private final Object data;

    public TypeUserNotSaveException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

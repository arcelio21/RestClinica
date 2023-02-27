package com.example.exception.user.type_user;

public class TypeUserNotUpdateException extends RuntimeException{

    private final Object data;

    public TypeUserNotUpdateException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public TypeUserNotUpdateException(String message) {
        super(message);
        this.data = null;
    }

    public Object getData() {
        return data;
    }
}

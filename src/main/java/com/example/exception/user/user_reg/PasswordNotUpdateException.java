package com.example.exception.user;

public class PasswordNotUpdateException extends RuntimeException{


    private final Object data;

    public PasswordNotUpdateException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public PasswordNotUpdateException(String message) {
        super(message);
        this.data = null;
    }

    public Object getData() {
        return data;
    }
}

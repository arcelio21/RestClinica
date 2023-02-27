package com.example.exception.user;

public class UserNotSaveException extends  RuntimeException{

    private final Object data;
    public UserNotSaveException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public UserNotSaveException(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}

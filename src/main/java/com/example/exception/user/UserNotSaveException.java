package com.example.exception.user;

public class UserNotSaveException extends  Exception{

    private final Object data;
    public UserNotSaveException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public final Object getData() {
        return data;
    }
}

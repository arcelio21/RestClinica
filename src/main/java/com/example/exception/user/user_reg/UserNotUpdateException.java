package com.example.exception.user;

import com.example.dto.user.user_reg.UserRegUpdateDto;

public class UserNotUpdateException extends RuntimeException{

    private final Object data;
    public UserNotUpdateException(String message) {
        super(message);
        this.data=null;
    }

    public UserNotUpdateException(String message, UserRegUpdateDto user) {
        super(message);
        this.data=user;
    }

    public Object getData() {
        return data;
    }
}

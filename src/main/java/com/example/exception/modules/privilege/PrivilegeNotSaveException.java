package com.example.exception.modules.privilege;

public class PrivilegeNotSaveException extends RuntimeException{

    private final Object data;

    public PrivilegeNotSaveException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

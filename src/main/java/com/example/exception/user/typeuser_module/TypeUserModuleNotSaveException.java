package com.example.exception.user.typeuser_module;

public class TypeUserModuleNotSaveException extends RuntimeException{

    private Object data;

    public TypeUserModuleNotSaveException(Object data,String message) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

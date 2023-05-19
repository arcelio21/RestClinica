package com.example.exception.modules.modules;

public class ModulesNoFoundException extends RuntimeException{


    private Object data;

    public ModulesNoFoundException(String message, Object data){

        super(message);
        this.data = data;
    }

    public ModulesNoFoundException(String message){

        super(message);
        this.data = null;
    }

    public Object getData() {
        return data;
    }

    
}

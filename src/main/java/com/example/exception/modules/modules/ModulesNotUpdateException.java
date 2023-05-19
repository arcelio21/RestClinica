package com.example.exception.modules.modules;

public class ModulesNotUpdateException extends RuntimeException{
    
    private Object data;

    public ModulesNotUpdateException(String arg0, Object data) {
        super(arg0);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}

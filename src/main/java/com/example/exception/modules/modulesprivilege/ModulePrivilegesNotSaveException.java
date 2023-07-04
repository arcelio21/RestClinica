package com.example.exception.modules.modulesprivilege;

public class ModulePrivilegesNotSaveException extends RuntimeException{

    private final Object data;

    public ModulePrivilegesNotSaveException(String message, Object data) {
        super(message);
        this.data = data;
    }


    public Object getData() {
        return data;
    }
}

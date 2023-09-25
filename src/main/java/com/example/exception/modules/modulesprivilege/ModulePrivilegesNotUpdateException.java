package com.example.exception.modules.modulesprivilege;

public class ModulePrivilegesNotUpdateException extends RuntimeException{

    private final Object data;

    public ModulePrivilegesNotUpdateException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

package com.example.exception.modules.privilege;

/**
 * Excepción para indicar que no se pudo actualizar un privilegio.
 */
public class PrivilegeNotUpdateException extends RuntimeException{

    private final Object data;

    public PrivilegeNotUpdateException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

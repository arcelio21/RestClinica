package com.example.exception.modules.modules;

/*
 * CLASE QUE CAPTURARA EXCEPCIONES DE ERROR DE GUARDADO PARA MODULOS
 * 
 */
public class ModulesNotSaveException extends RuntimeException{
    
    private Object data;

    public ModulesNotSaveException(String arg0, Object data) {
        super(arg0);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    
}

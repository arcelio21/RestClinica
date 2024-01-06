package com.example.exception.speciality.user_speciality;

import lombok.Getter;

/**
 * Clase de excepción personalizada, {@code UserSpecialityNotSaveException}, que extiende {@code RuntimeException}.
 * Representa excepciones relacionadas con la invalidación de la operación de guardar la especialidad de un usuario.
 * Encapsula información adicional mediante el atributo {@code data}.
 *
 * Esta excepción puede construirse de dos maneras:
 *   - Constructor con un mensaje personalizado: {@code UserSpecialityNotSaveException(String message, Object data)}
 *   - Constructor con un mensaje predeterminado: {@code UserSpecialityNotSaveException(Object data)}
 */
@Getter
public class UserSpecialityNotSaveException extends RuntimeException {
 
    private Object data;

    public UserSpecialityNotSaveException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public UserSpecialityNotSaveException(Object data) {
        super("SAVE NOT VALID");
        this.data = data;
    }
}

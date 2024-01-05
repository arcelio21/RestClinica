package com.example.exception.speciality.user_speciality;

import lombok.Getter;

/**
 * Clase de excepción personalizada, {@code UserSpecialityNotUpdateException}, que extiende {@code RuntimeException}.
 * Representa excepciones relacionadas con la invalidación de la actualización de la especialidad de un usuario.
 * Encapsula información adicional mediante el atributo {@code data}.
 *
 * Esta excepción puede construirse de dos maneras:
 *   - Constructor con un mensaje personalizado: {@code UserSpecialityNotUpdateException(String message, Object data)}
 *   - Constructor con un mensaje predeterminado: {@code UserSpecialityNotUpdateException(Object data)}
 */
@Getter
public class UserSpecialityNotUpdateException extends RuntimeException{
   
    private final Object data;

    public UserSpecialityNotUpdateException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public UserSpecialityNotUpdateException(Object data) {
        super("UPDATE NOT VALID");
        this.data = data;
    }

}

package com.example.exception.speciality.speciality;

import lombok.Getter;

/**
 * Clase de excepción personalizada, {@code SpecialityNotUpdateException}, que extiende {@code RuntimeException}.
 * Representa excepciones relacionadas con la invalidación de la actualización de una especialidad.
 * Encapsula información adicional mediante el atributo {@code data}.
 *
 * <p>
 * Esta excepción puede construirse de dos maneras:
 * <ul>
 *     <li>Constructor con un mensaje personalizado: {@code SpecialityNotUpdateException(String message, Object data)}</li>
 *     <li>Constructor con un mensaje predeterminado: {@code SpecialityNotUpdateException(Object data)}</li>
 * </ul>
 * </p>
 */
@Getter
public class SpecialityNotUpdateException extends RuntimeException{

    private final Object data;

    public SpecialityNotUpdateException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public SpecialityNotUpdateException(Object data) {
        super("UPDATE NOT VALID");
        this.data = data;
    }
}

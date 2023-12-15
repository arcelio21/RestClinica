package com.example.exception.speciality.speciality;

import lombok.Getter;

/**
 * Clase de excepción personalizada, {@code SpecialityNotSaveException}, que extiende {@code RuntimeException}.
 * Representa excepciones relacionadas con el fallo al guardar una especialidad.
 * Encapsula información adicional mediante el atributo {@code data}.
 *
 * <p>
 * Esta excepción puede construirse de dos maneras:
 * <ul>
 *     <li>Constructor con un mensaje personalizado: {@code SpecialityNotSaveException(Object data, String message)}</li>
 *     <li>Constructor con un mensaje predeterminado: {@code SpecialityNotSaveException(Object data)}</li>
 * </ul>
 * </p>
 */
@Getter
public class SpecialityNotSaveException extends RuntimeException{

    private final Object data;

    public SpecialityNotSaveException(Object data,String message) {
        super(message);
        this.data = data;
    }

    public SpecialityNotSaveException(Object data) {
        super("ERROR SAVED");
        this.data = data;
    }
}

package  com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @apiNote Mostrara las excepcion cuando los datos para el jwt no existe
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public  class ApiUnauthorized  extends  Exception{

    public ApiUnauthorized(String message) {
        super(message);
    }
}
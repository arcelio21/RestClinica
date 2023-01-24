package com.example.validator;

import com.example.exception.ApiUnauthorized;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

@Component
public class AuthValidator {

    /**
     * Es el argumento que se envia por la URL  de la peticion para validar que se quiere obtener un token,
     * en caso de que el valor de este argumento sea secreto valida que la persona tiene el permiso de hacer peticiones para obtener el token
     */
    private static  final String CLIENT_CREDENTIALS = "arcelio";

    /**
     * Validara si los datos de enviados para obtener token son validos
     * @param paramMap
     * @param grantType
     * @throws ApiUnauthorized
     */
    public void validate(MultiValueMap<String,String> paramMap,String grantType) throws ApiUnauthorized {

        if(!grantType.equals(AuthValidator.CLIENT_CREDENTIALS)){
            this.message("El campo granType es invalido");
        }

        if(Objects.isNull(paramMap) || Objects.isNull(paramMap.getFirst("client_id")) || paramMap.getFirst("cliente_id").isEmpty()
                || Objects.isNull(paramMap.getFirst("client_secret")) || paramMap.getFirst("cliente_secret").isEmpty()){
            this.message("client_id y/o client secrete es invalido");
        }
    }

    /**
     * @apiNote Enviar y mostrar error de tipo ApiUnauthorized
     */
    private void message(String message) throws ApiUnauthorized{
        throw new ApiUnauthorized(message);
    }
}

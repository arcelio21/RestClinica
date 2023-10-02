package com.example.dto.user.type_user_reg;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Clase de DTO que representa los datos de un usuario y su tipo de usuario asociado.
 * @param id
 * @param fullName
 * @param idenCard
 * @param typeUser
 * @param status
 */
@Schema(name = "UserTypeRegGetDto", description = "Datos de usuario y su tipo de usuario asociado")
public record UserTypeRegGetDto(

        @Schema(name = "id", description = "ID de registro", example = "1") 
        Long id,

        @Schema(name = "fullName", description = "Nombre completo de usuario", example = "Arcelio Montezuma") 
        String fullName,

        @Schema(name = "idenCard", description = "Identificiacion de usuario legal", example = "12000704001435") 
        Long idenCard,

        @Schema(name = "typeUser", description = "Nombre de tipo de usuario", example = "Pacient") 
        String typeUser,

        @Schema(name = "status", description = "Nombre de estado", example = "Activated") 
        String status) {

}
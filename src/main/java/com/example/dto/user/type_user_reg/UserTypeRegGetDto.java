package com.example.dto.user.type_user_reg;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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

        @Min(value = 1)
        @Schema(name = "id", description = "ID de registro", example = "1") 
        Long id,

        @NotBlank
        @Schema(name = "fullName", description = "Nombre completo de usuario", example = "Arcelio Montezuma") 
        String fullName,

        @NotNull
        @Length(min = 12)
        @Schema(name = "idenCard", description = "Identificiacion de usuario legal", example = "12000704001435") 
        Long idenCard,

        @NotBlank
        @Schema(name = "typeUser", description = "Nombre de tipo de usuario", example = "Pacient") 
        String typeUser,

        @NotBlank
        @Schema(name = "status", description = "Nombre de estado", example = "Activated") 
        String status) {

}
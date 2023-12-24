package com.example.dto.speciality.userspeciality;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(name = "UserSpecialityBySpecialityGet",
        description = "Estructura para datos de especialidad asociado a un usuario filtrado por tipo de especialidad"
)
public record UserSpecialityBySpecialityGetDto(
        @Schema(name = "id", description = "ID de recurso", example = "1")
        @Min(1)
        @NotNull
        Long id,
        @Schema(name = "nameStatus",description = "Descripcion de estado de la especialidad asociada", example = "Activated")
        @NotBlank
        String nameStatus,
        @Schema(name = "nameUser", description = "Nombre de usuario con especialidad asociada", example = "Arcelio")
        @NotBlank
        String nameUser,
        @Schema(name = "lastNameUser", description = "Apellido de usuario con especialidad asociado", example = "Montezuma")
        @NotBlank
        String lastNameUser,
        @Schema(name = "nameTypeUser", description = "Nombre de tipo de usuario asociado a usuario", example = "Medico")
        @NotBlank
        String nameTypeUser
) {
}

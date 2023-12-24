package com.example.dto.speciality.userspeciality;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(
        name = "UserSpecialityByTypeUserGet",
        description = "Estructura para datos de usuario asociado a especialidad filtrado por tipo de usuario"
)
public record UserSpecialityByTypeUserGetDto(
        @Schema(name = "id", description = "ID de recurso", example = "1")
        @Min(1)
        @NotNull
        Long id,
        @Schema(name = "nameSpeciality", description = "Nombre de especialidad asociada a usuario", example = "Odontologo")
        @NotBlank
        String nameSpeciality,
        @Schema(name = "nameStatus",description = "Descripcion de estado de la especialidad asociada", example = "Activated")
        @NotBlank
        String nameStatus,
        @Schema(name = "nameUser", description = "Nombre de usuario con especialidad asociada", example = "Arcelio")
        @NotBlank
        String nameUser,
        @Schema(name = "lastNameUser", description = "Apellido de usuario con especialidad asociado", example = "Montezuma")
        @NotBlank
        String lastNameUser
) {
}

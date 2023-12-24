package com.example.dto.speciality.userspeciality;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Schema(
        name = "UserSpecialityByUserTypeRegGet",
        description = "Estructura de datos para usuario asociado a especialidad filtrado por ID de usuario asociado a un tipo de usuario"
)
public record UserSpecialityByUserTypeRegGetDto(
        @Schema(name = "id", description = "ID de recurso", example = "1")
        @Min(1)
        @NotNull
        Long id,
        @Schema(name = "nameSpeciality", description = "Nombre de especialidad asociada a usuario", example = "Odontologo")
        @NotBlank
        String nameSpeciality,
        @Schema(name = "nameStatus",description = "Descripcion de estado de la especialidad asociada", example = "Activated")
        @NotBlank
        String nameStatus
) {
}

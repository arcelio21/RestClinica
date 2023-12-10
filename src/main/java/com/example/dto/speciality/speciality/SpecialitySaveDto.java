package com.example.dto.speciality.speciality;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(name = "SpecialitySave", description = "Estructura para guadar nuevo tipo de especialidad")
public record SpecialitySaveDto(
        @Schema(name = "name", description = "Nombre del tipo de especialidad", example = "Odontologo")
        @NotBlank
        String name
) {
}

package com.example.dto.speciality.speciality;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(name = "SpecialityGet", description = "DTO para obtener datos de tipos de especialidades")
public record SpecialityGetDto(

        @Schema(name = "id", description = "ID unico para el tipo de especialidad", example = "1")
        @NotNull
        @Min(1)
        Integer id,

        @Schema(name = "name", description = "Nombre del tipo de especialidad", example = "Odontologo")
        @NotBlank
        String name
) {
}

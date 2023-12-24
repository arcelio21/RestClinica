package com.example.dto.speciality.userspeciality;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Schema(
        name = "UserSpecialityUpdate",
        description = "Estructura para guardado de usuario asociado a especialidad"
)
public record UserSpecialitySaveDto(
        @Schema(name = "idSpeciality", description = "ID de especialidad", example = "1")
        @Min(1)
        @NotNull
        Integer idSpeciality,
        @Schema(name = "idUserTypeReg", description = "ID de usuario asociado a tipo de usuario", example = "1")
        @Min(1)
        @NotNull
        Long idUserTypeReg,
        @Schema(name = "idStatus", description = "ID de tipo de estado", example = "1")
        @Min(1)
        @NotNull
        Integer idStatus
) {
}

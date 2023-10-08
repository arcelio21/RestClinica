package com.example.dto.user.type_user;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TypeUserSaveDto", description = "DTO para asociar tipos de usuario a usuario")
public record TypeUserSaveDto(
    @NotNull(message = "ID no puede ser nulo ni vacio")
    @Min(value = 1, message = "El valor no puede ser menor a 1")
    @Schema(name = "id",description = "Identifacion unica de tipo de usuario", example = "1")
    Integer id
) {
}

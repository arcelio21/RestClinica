package com.example.dto.modules.modulesprivileges;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PrivilegeIdDto", description = "Dto para almacenar ID de privilegio asociado a un modulo")
public record PrivilegeIdDto(
    

    @NotNull
    @Min(value = 1)
    @Schema(name = "id", description = "Valor de privilegio asociados", example = "1")
    @JsonProperty(value = "id")
    Integer value
) {
    
}

package com.example.dto.modules.privileges;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Schema(name = "PrivilegesUpdate", description = "Estructura de informacion para actualizar privileges")
public class PrivilegeUpdateDto {

    @Schema(name = "id", description = "Valor único de indetificacion")
    @NotNull(message = "Valor de ID no valido")
    @Min(value = 1, message = "Valor de ID no valido")
    private Integer id;

    @Schema(name = "name", description = "Nombre de privilegio")
    @NotBlank(message = "Campo debe contener información")
    private String name;
}

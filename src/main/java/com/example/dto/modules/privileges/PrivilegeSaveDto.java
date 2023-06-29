package com.example.dto.modules.privileges;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Schema(name = "PrivilegesSave", description = "Para definir estructura de informacion necesaria para guardar privileges")
public class PrivilegeSaveDto {

    @Schema(name = "Name", description = "Nombre de privilegio")
    @NotBlank(message = "Campo no debe estar vacio")
    private String name;
}

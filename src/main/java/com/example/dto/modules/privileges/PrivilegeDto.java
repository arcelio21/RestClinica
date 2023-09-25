package com.example.dto.modules.privileges;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(name = "Privilege_Get_DTO", description = "Se utilizara para pateciones de tipo GET")
public class PrivilegeDto {

    @Schema(name = "ID", description = "Valor único de indetificacion")
    private Integer id;

    @Schema(name = "Name", description = "Nombre de privilegio")
    private String name;
}

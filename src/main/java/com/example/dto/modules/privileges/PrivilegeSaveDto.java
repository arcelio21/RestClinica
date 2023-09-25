package com.example.dto.modules.privileges;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@JsonDeserialize(builder = PrivilegeSaveDto.PrivilegeSaveDtoBuilder.class)
@Schema(name = "PrivilegeSave", description = "Para definir estructura de informacion necesaria para guardar privileges")
public class PrivilegeSaveDto {

    @Schema(description = "Nombre de privilegio")
    @NotBlank(message = "Campo no debe estar vacio")
    @JsonProperty("name")
    private String name;
}

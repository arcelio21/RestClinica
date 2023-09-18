package com.example.controller.user;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.user.user_reg.UserRegDto;
import com.example.service.user.ServiceTypeUserModuleImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/typeusermodule")
@RequiredArgsConstructor
@Tag(name = "Controller Type User Module", description = "Interactuar con informacion de los tipos de usuarios asociados a modulos")
public class ControllerTypeUserModule extends ControllerTemplate {


    private ServiceTypeUserModuleImpl serviceTypeUserModule;

    @Operation(summary = "Todos los tipos de  usuarios asignados a modulos ", description = "Muestra informacion de los tipos de usuarios que se le " +
            "asgino modulos con sus privilegios",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true,
                    content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    }
    )
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){

        return ResponseEntity.ok(ResponseDTO.builder()
                        .info("Tipos de usuarios con sus modulos asociados")
                        .data(this.serviceTypeUserModule.getAll())
                .build());
    }
}
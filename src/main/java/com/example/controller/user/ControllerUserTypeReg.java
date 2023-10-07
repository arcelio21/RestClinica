package com.example.controller.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.service.user.ServiceUserTypeRegImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usertypereg")
public class ControllerUserTypeReg extends ControllerTemplate {

    private final ServiceUserTypeRegImpl service;


    @Operation(summary = "Obtener todas las usuarios y su tipo de usuario", description = "Se utiliza para obtener todos los usuarios con tipo de usuario registradas",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true,
                    content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            })
    }
    )
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Informacion obtenidad")
                        .data(service.getAll())
                        .build());
    }
}

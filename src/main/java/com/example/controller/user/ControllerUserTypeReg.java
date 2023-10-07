package com.example.controller.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.user.type_user_reg.UserTypeRegSaveDto;
import com.example.dto.user.type_user_reg.UserTypeRegUpdateDto;
import com.example.service.user.ServiceUserTypeRegImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;


@Tag(name = "Controller UserTypeReg", description = "Realiza interaccion con la informacion de los usuario asociados a un tipo de usuario")
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
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Informacion obtenidad")
                        .data(service.getAll())
                        .build());
    }

    @Operation(summary = "Obtiene usuario con tipo de usuario asociado filtrado por ID de registro",description = "Cuando se desee un registro especifico se enviada el ID de este registro como parametro",
                method = "GET", responses = {
                @ApiResponse(responseCode = "200",description = "Datos de usuario y su tipo de usuario asociado encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDTO.class,description = "Datos de usuario y su tipo de usuario asociado"))),
                @ApiResponse(responseCode = "404",description = "Data de usuario no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error")))
    },parameters = {
                @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Long id){
        
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Informacion obtenidad")
                        .data(this.service.getById(id))
                        .build());
    }

    @Operation(
    summary = "Guarda registro de usuario con tipo de usuario asociado",
    description = "Este permitira poder asociar un tipo de usuario a un usuario y guardar un registro de esto",
    responses = {
            @ApiResponse(responseCode = "201", description = "registro creada correctamente",content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                   @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    },requestBody =
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Estructura de datos a guardar", required = true,
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
                        schema = @Schema(implementation = UserTypeRegSaveDto.class)))
    
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody UserTypeRegSaveDto data){
     
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Cantidad de registros guardados ")
                        .data(this.service.save(data))
                        .build()); 
    }

    @Operation(
        summary = "Actualiza registro de usuario asociado con tipo de usuario",
        description = "Para actualizar la asociacion de un tipo de usuario con un usuario",
        responses = {
                @ApiResponse(responseCode = "200", description = "Registro actualizada correctamente",content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ResponseDTO.class))
                }),
                @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                })
        }, requestBody =
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Estructura de datos a actualizar", required = true,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserTypeRegUpdateDto.class)))
    )
    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody UserTypeRegUpdateDto dtoUpdate){
     
        return ResponseEntity.ok(
                        ResponseDTO.builder()
                                        .info("Cantidad de registros actualizados")
                                        .data(this.service.update(dtoUpdate))
                                        .build()
        );
    }
}

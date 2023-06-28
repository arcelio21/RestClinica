package com.example.controller.module;

import com.example.dto.ErrorResponseDto;
import com.example.dto.ValidateGroupA;
import com.example.dto.modules.ModulesDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.service.modules.ServiceModuleImple;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/module")
@Tag(name = "Controller Modules", description = "Realiza operaciones para modificar y obtener informacion de los modulos")
public class ControllerModules extends ControllerTemplate{
    

    private ServiceModuleImple serviceModuleImple;

    @Operation(
            summary = "Obtener todos los modulos",
            description = "Se utiliza para obtener lista de modulos registrados, segun el usuario que lo consulte",
            method = "Get",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Búsqueda exitosa",
                            useReturnTypeSchema = true,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontro modulos disponibles",
                            useReturnTypeSchema = true,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "NO tiene permisos para acceder a esta ruta",
                            useReturnTypeSchema = true,
                            content = @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                    schema = @Schema
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Es necesario que se autentifique para poder acceder a la operacion de esta ruta",
                            useReturnTypeSchema = true,
                            content = @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                    schema = @Schema
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        
        return ResponseEntity.ok(
            ResponseDTO.builder()
                .info("Datos disponibles")
                .data(this.serviceModuleImple.getAll())
                .build()
        );
        
    }

    @Operation(
            summary = "Obtener modulos por ID",
            description = "Se obtiene los modulos filtrado por el ID que el usuario proporciones",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Modulo encontrado",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ModulesDto.class,
                                            description = "Datos de modulo"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Modulo no encontrado, Id no valido",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class,
                                            description = "Datos de error"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "NO tiene permisos para acceder a esta ruta",
                            useReturnTypeSchema = true,
                            content = @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                    schema = @Schema
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Es necesario que se autentifique para poder acceder a la operacion de esta ruta",
                            useReturnTypeSchema = true,
                            content = @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                    schema = @Schema
                            )
                    )

            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@RequestParam("id") Long id){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Datos encontrados")
                        .data(this.serviceModuleImple.getById(id))
                        .build()
        );
    }

    @Operation(
            summary = "Actualizar modules",
            description = "Se actualizara los modulos que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Modulo actualizada correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    public ResponseEntity<ResponseDTO> update(@Validated(value = {ValidateGroupA.class}) @RequestBody ModulesDto modulesDto){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Actualización exitosa, cantidad de registros actualizados")
                        .data(this.serviceModuleImple.update(modulesDto))
                        .build()
        );
    }
}

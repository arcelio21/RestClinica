package com.example.controller.module;

import com.example.dto.ErrorResponseDto;
import com.example.dto.modules.ModuleSaveDto;
import com.example.dto.modules.ModulesDto;
import com.example.dto.modules.ModulesUpdateDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

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
                            useReturnTypeSchema = true
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<ModulesDto>>> getAll(){
        
        return ResponseEntity.ok(
            ResponseDTO.<List<ModulesDto>>builder()
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
                            useReturnTypeSchema = true
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
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<ModulesDto>> getById(@PathVariable("id") Long id){

        return ResponseEntity.ok(
                ResponseDTO.<ModulesDto>builder()
                        .info("Datos encontrados")
                        .data(this.serviceModuleImple.getById(id))
                        .build()
        );
    }

    @Operation(
            summary = "Actualizar modules",
            description = "Se actualizara los modulos que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Modulo actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ModulesUpdateDto.class)
                                        )
                        )
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@Validated @RequestBody ModulesUpdateDto modulesDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Actualización exitosa, cantidad de registros actualizados")
                        .data(this.serviceModuleImple.update(modulesDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar modules",
            description = "Se guardara los modulos que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Modulo guardado correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ModuleSaveDto.class)
                                        )
                        )
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@Validated @RequestBody ModuleSaveDto modulesDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros guardados")
                        .data(this.serviceModuleImple.save(modulesDto))
                        .build()
        );
    }
}

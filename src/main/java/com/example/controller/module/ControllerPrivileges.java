package com.example.controller.module;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.modules.privileges.PrivilegeDto;
import com.example.dto.modules.privileges.PrivilegeSaveDto;
import com.example.dto.modules.privileges.PrivilegeUpdateDto;
import com.example.service.modules.IServicePrivilege;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Tag(name = "Controller Privileges", description = "Realiza operacion para modificar u obtener informacion de privileges")
@RestController
@RequestMapping("api/v1/privileges")
public class ControllerPrivileges extends ControllerTemplate {

    private final IServicePrivilege servicePrivilege;

    @Operation(
            summary = "Obtener todos los privilege",
            description = "Se utiliza para obtener lista de privilege creados, segun el usuario que lo consulte",
            method = "Get",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Búsqueda exitosa",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontro privilege disponibles",
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
    public ResponseEntity<ResponseDTO<List<PrivilegeDto>>> getAll(){

        return ResponseEntity.ok(
                ResponseDTO.<List<PrivilegeDto>>builder()
                        .info("Datos disponibles")
                        .data(this.servicePrivilege.getAll())
                        .build()
        );

    }

    @Operation(
            summary = "Obtener privilege por ID",
            description = "Se obtiene los privilege filtrado por el ID que el usuario proporciones",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Privilege encontrado",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Privilege no encontrado, Id no valido",
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
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema()
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Es necesario que se autentifique para poder acceder a la operacion de esta ruta",
                            useReturnTypeSchema = true,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema
                            )
                    )

            }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<PrivilegeDto>> getById(@PathVariable("id") Integer id){

        return ResponseEntity.ok(
                ResponseDTO.<PrivilegeDto>builder()
                        .info("Datos encontrados")
                        .data(this.servicePrivilege.getById(id))
                        .build()
        );
    }

    @Operation(
            summary = "Actualizar privilege",
            description = "Se actualizara los privilege que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Privilege actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@Validated @RequestBody PrivilegeUpdateDto privilegeUpdateDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Actualización exitosa, cantidad de registros actualizados")
                        .data(this.servicePrivilege.update(privilegeUpdateDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar privileges",
            description = "Se guardara los privileges que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Privilege guardado correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@Validated @RequestBody PrivilegeSaveDto privilegeSaveDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros guardados")
                        .data(this.servicePrivilege.save(privilegeSaveDto))
                        .build()
        );
    }
}

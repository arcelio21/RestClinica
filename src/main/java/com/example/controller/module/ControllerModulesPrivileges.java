package com.example.controller.module;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.ValidateGroupA;
import com.example.dto.modules.ModulesDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.service.modules.IServiceModulePrivilege;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Tag(name = "Controller Modules Privileges", description = "Realiza operaciones de manipulacion y consultas de datos de ModulePrivileges")
@RestController
@RequestMapping("/api/v1/moduleprivileges")
public class ControllerModulesPrivileges extends ControllerTemplate {

    private final IServiceModulePrivilege serviceModulePrivilege;

    @Operation(
            summary = "Obtener todos los Privilegios de modulos",
            description = "Se utiliza para obtener lista de Privilegios de modulos registrados, segun el usuario que lo consulte",
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
                            description = "No se encontro privilegios de modulos disponibles",
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
                        .data(this.serviceModulePrivilege.getAll())
                        .build()
        );

    }


    @Operation(
            summary = "Obtener privilegio de modulo por ID",
            description = "Se obtiene los privilegio de modulo filtrado por el ID que el usuario proporciones",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Modulo encontrado",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ModulesDto.class,
                                            description = "Datos de privilegio de modulo"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Privilegio de Modulo no encontrado, Id no valido",
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
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Long id){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Datos encontrados")
                        .data(this.serviceModulePrivilege.getById(id))
                        .build()
        );
    }


    @Operation(
            summary = "Actualizar privilegio de module",
            description = "Se actualizara privilegio de  modulo que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Privilegio de Modulo actualizada correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PutMapping
    public ResponseEntity<ResponseDTO> update( @Validated @RequestBody ModulePrivilegeUpdateDto modulePrivilegeUpdateDto){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Actualización exitosa, cantidad de registros actualizados")
                        .data(this.serviceModulePrivilege.update(modulePrivilegeUpdateDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar privilegio de module",
            description = "Se guardara privilegio de modulo que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Privilegio de Modulo guardado correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> save(@Validated @RequestBody ModulePrivilegeSaveDto modulePrivilegeSaveDto){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Cantidad de registros guardados")
                        .data(this.serviceModulePrivilege.save(modulePrivilegeSaveDto))
                        .build()
        );
    }
}

package com.example.controller.module;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.dto.modules.modulesprivileges.PrivilegeModuleDetailGetDto;
import com.example.service.modules.IServiceModulePrivilege;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
                            useReturnTypeSchema = true
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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<ModulePrivilegesDto>>> getAll(){

        return ResponseEntity.ok(
                ResponseDTO.<List<ModulePrivilegesDto>>builder()
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
                            useReturnTypeSchema = true
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
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<ModulePrivilegesDto>> getById(@PathVariable("id") Long id){

        return ResponseEntity.ok(
                ResponseDTO.<ModulePrivilegesDto>builder()
                        .info("Datos encontrados")
                        .data(this.serviceModulePrivilege.getById(id))
                        .build()
        );
    }


    @Operation(
            summary = "Actualizar privilegio de module",
            description = "Se actualizara privilegio de  modulo que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Privilegio de Modulo actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update( @Validated @RequestBody ModulePrivilegeUpdateDto modulePrivilegeUpdateDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Actualización exitosa, cantidad de registros actualizados")
                        .data(this.serviceModulePrivilege.update(modulePrivilegeUpdateDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar privilegio de module",
            description = "Se guardara privilegio de modulo que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Privilegio de Modulo guardado correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@Validated @RequestBody ModulePrivilegeSaveDto modulePrivilegeSaveDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros guardados")
                        .data(this.serviceModulePrivilege.save(modulePrivilegeSaveDto))
                        .build()
        );
    }

    @Operation(summary = "Obtener detalles de privilegios de modulo",description = "Ver informacion completa de los privilegios asignados a modulo",
                method = "GET", responses = {
                @ApiResponse(responseCode = "200",description = "Lista de privilegios de modulo encontrado",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "404",description = "data no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error")))
    }
    )
    @GetMapping(path = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<PrivilegeModuleDetailGetDto>>> getPrivilegeModuleDetails(){
    
        return ResponseEntity.ok(
                ResponseDTO.<List<PrivilegeModuleDetailGetDto> >builder()
                        .info("Informacion obtenidad")
                        .data(this.serviceModulePrivilege.getPrivilegeModuleDetails())
                        .build());
    }

    @Operation(summary = "Obtiene informacion completa de privilegios sobre modulo",description = "Si se desea ver informacion de los modulos que se le han asigando privilgios",
                method = "GET", responses = {
                @ApiResponse(responseCode = "200",description = "Datos encontrado",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "404",description = "Datos no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error")))
    },parameters = {
                @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<PrivilegeModuleDetailGetDto>> getPrivilegeModuleDetailsById(@NotNull @Min(1) @PathVariable("id")Long id){
     
        return ResponseEntity.ok(
                ResponseDTO.<PrivilegeModuleDetailGetDto>builder()
                        .info("Informacion obtenidad con id: " + id)
                        .data(this.serviceModulePrivilege.getPrivilegeModuleDetailsById(id))
                        .build()); 
    }

    @Operation(summary = "Obtiene detalles de privilegios de modulo por id de modulo",description = "Ver detalle completo de los privilegios que tiene un modulo filtrado por id de modulo", 
                method = "GET", responses = { 
                @ApiResponse(responseCode = "200",description = "Data de privilegio de modulo encontrado",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "404",description = "data no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error"))) 
    },parameters = {
                @Parameter(name = "idModule", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "details/module/{idModule}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<PrivilegeModuleDetailGetDto> >> getPrivilegeModuleDetailsByModuleId(@NotNull @Min(1) @PathVariable("idModule") Long idModule){
     
        return ResponseEntity.ok(
                ResponseDTO.<List<PrivilegeModuleDetailGetDto> >builder()
                        .info("Informacion obtenidad con id: " + idModule)
                        .data(this.serviceModulePrivilege.getPrivilegeModuleDetailsByModuleId(idModule))
                        .build()); 
    }

}

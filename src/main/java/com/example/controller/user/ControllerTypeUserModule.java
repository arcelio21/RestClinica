package com.example.controller.user;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.user.typeuser_module.*;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/typeusermodule")
@RequiredArgsConstructor
@Tag(name = "Controller Type User Module", description = "Interactuar con informacion de los tipos de usuarios asociados a modulos")
public class ControllerTypeUserModule extends ControllerTemplate {


    private final ServiceTypeUserModuleImpl serviceTypeUserModule;

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

    @Operation(summary = "Tipo de usuario asignados a modulos filtrado por ID de registro",description = "Muestra informacion de tipo de usuario que se le " +
            "asigno modulos con sus privilegios filtrado por ID de registro",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TypeUserModuleGetDto.class,description = "Datos de Tipo de usuario asignados a modulo"))),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable(name = "id") Long id ){

        return ResponseEntity.ok(ResponseDTO.builder()
                .info("Tipos de usuarios con sus modulos asociados filtrado por ID de registro")
                .data(this.serviceTypeUserModule.getById(id))
                .build());
    }

    @Operation(
            summary = "Actualizar modulos asignados a un tipo de usuario",
            description = "Se actualizara modulo asignado a  tipo de usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro actualizada correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PutMapping
    public ResponseEntity<ResponseDTO> update(@Validated @NotNull @RequestBody TypeUserModuleUpdateDto updateDto){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Datos actualizados correctamente")
                        .data(this.serviceTypeUserModule.update(updateDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar registro Modulos para tipo de usuario",
            description = "Se asignara un nuevo de registro donde se le asigna un modulo a un tipo de usuario",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Registro creada correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> save(@Validated @NotNull @RequestBody TypeUserModuleSaveDto saveDto){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Datos guardado correctamente")
                        .data(this.serviceTypeUserModule.save(saveDto))
                        .build()
        );
    }


    @Operation(summary = "Modulos asignados a tipos de usuario ", description = "Muestra todos los modulos que existen y que tipo de usuario lo puede acceder",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true,
                    content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ModuleTypeUserGetDto.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    }
    )
    @GetMapping("/moduleTypeUser")
    public ResponseEntity<ResponseDTO> getModuleAndTypeUserDistinct(){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Registros obtenidos")
                        .data(this.serviceTypeUserModule.getModuleAndTypeUserDistinct())
                        .build()
        );
    }

    @Operation(summary = "Modulos asignados a tipos de usuario filtrado por ID de tipo de usuario",
            description = "Muestra todos los modulos que han sido asignado a un tipo de usuario y que siga activado",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ModuleOfTypeUserGetDto.class,description = "Datos de Tipo de usuario"))),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idTypeUser", in = ParameterIn.PATH, description = "ID de tipo de usuario",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))
    }
    )
    @GetMapping("/moduleTypeUser/{idTypeUser}")
    public ResponseEntity<ResponseDTO> getModuleDistinctByIdTypeUserAndStatusActived(@NotNull @Min(1) @PathVariable("idTypeUser") Integer idTypeUser){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Registros obtenidos")
                        .data(this.serviceTypeUserModule.getModuleDistinctByIdTypeUserAndStatusActived(idTypeUser))
                        .build()
        );
    }


    @Operation(summary = "Modulos asignados a tipos de usuario filtrado por ID de tipo de usuario y estado",
            description = "Muestra todos los modulos que han sido asignado a un tipo de usuario pendiendo del estado que se haya pedido",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ModuleOfTypeUserGetDto.class,description = "Datos modulo de Tipo de usuario"))),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idTypeUser", in = ParameterIn.PATH, description = "ID de tipo de usuario",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32")),
            @Parameter(name = "idStatus", in = ParameterIn.PATH, description = "ID de estado",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))

    }
    )
    @GetMapping("/moduleTypeUser/{idTypeUser}/status/{idStatus}")
    public ResponseEntity<ResponseDTO> getModuleDistinctByIdTypeUserAndIdStatus(
            @PathVariable(name="idTypeUser") @NotBlank Integer idTypeUser,
            @PathVariable(name = "idStatus") @NotBlank Integer idStatus){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser, idStatus))
                        .build()
        );
    }
    @Operation(summary = "Tipos de usuarios asignados a un modulo segun su estado",
            description = "Muestra los tipos de usuarios que se han asignado a un modulo y que estado tiene esta asignacion o privilegio",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TypeUserOfModuleGetDto.class,description = "Datos de Tipo de usuario"))),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Ids no validos",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idModule", in = ParameterIn.QUERY, description = "ID de module",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64")),
            @Parameter(name = "idStatus", in = ParameterIn.QUERY, description = "ID de status", example = "1", required = true, schema = @Schema(implementation = Integer.class, type = "int", format = "int32", defaultValue = "0"))
    }
    )
    @GetMapping("/typeUser")
    public ResponseEntity<ResponseDTO> getTypeUserDistinctByIdModuleAndIdStatus(
            @NotBlank @RequestParam("idModule") Long idModule,
            @NotBlank @RequestParam("idStatus") Integer idStatus){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getTypeUserDistinctByIdModuleAndIdStatus(idModule,idStatus))
                        .build()
        );

    }


    @Operation(summary = "Tipos de usuarios asignacion activada a un modulo",
            description = "Muestra los tipos de usuarios que se han asignado a un modulo y con asignacion o privilegio activado",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TypeUserOfModuleGetDto.class,description = "Modulos de un Tipo de usuario"))),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no validos",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idModule", in = ParameterIn.PATH, description = "ID de module",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "int", format = "int64"))
    }
    )
    @GetMapping("/typeUser/activated/module/{idModule}")
    public ResponseEntity<ResponseDTO> getTypeUserDistinctByIdModuleAndStatusActivated(@NotNull @Min(0) @PathVariable("idModule") Long idModule){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getTypeUserDistinctByIdModuleAndStatusActivated(idModule))
                        .build()
        );
    }

    @Operation(summary = "Privilegios sobre un modulo asociado a un tipo de usuario",
            description = "Muestra los privilegios y el estado de estos que tiene sobre un modulo un tipo de usuario",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PrivilegeOfModuleGetDto.class,description = "Datos de Privilegios"))),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Ids no validos",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idTypeUser", in = ParameterIn.QUERY, description = "ID type user",example = "1",required = true, schema = @Schema(implementation = Integer.class, type = "int", format = "int32")),
            @Parameter(name = "idModule", in = ParameterIn.QUERY, description = "ID de module", example = "1", required = true, schema = @Schema(implementation = Long.class, type = "long", format = "int64"))
    }
    )
    @GetMapping("/privilegeModule")
    public ResponseEntity<ResponseDTO> getPrivelegeOfModuleByIdTypeUserAndIdModuleAndStatusActived(
            @RequestParam(name = "idTypeUser") @NotBlank Integer idTypeUser,
            @RequestParam(name = "idModule") @NotBlank Long idModule){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getPrivelegeOfModuleByIdTypeUserAndIdModuleAndStatusActived(idTypeUser,idModule))
                        .build()
        );
    }
}
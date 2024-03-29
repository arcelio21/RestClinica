package com.example.controller.user;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.user.typeuser_module.*;
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

import java.util.List;

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
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TypeUserModuleGetDto>>> getAll(){

        return ResponseEntity.ok(ResponseDTO.<List<TypeUserModuleGetDto>>builder()
                        .info("Tipos de usuarios con sus modulos asociados")
                        .data(this.serviceTypeUserModule.getAll())
                .build());
    }

    @Operation(summary = "Tipo de usuario asignados a modulos filtrado por ID de registro",description = "Muestra informacion de tipo de usuario que se le " +
            "asigno modulos con sus privilegios filtrado por ID de registro",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<TypeUserModuleGetDto>> getById(@PathVariable(name = "id") Long id ){

        return ResponseEntity.ok(ResponseDTO.<TypeUserModuleGetDto>builder()
                .info("Tipos de usuarios con sus modulos asociados filtrado por ID de registro")
                .data(this.serviceTypeUserModule.getById(id))
                .build());
    }

    @Operation(
            summary = "Actualizar modulos asignados a un tipo de usuario",
            description = "Se actualizara modulo asignado a  tipo de usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }, requestBody =
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Estructura de datos a actualizar", required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeUserModuleUpdateDto.class)))
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@Validated @NotNull @RequestBody TypeUserModuleUpdateDto updateDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Datos actualizados correctamente")
                        .data(this.serviceTypeUserModule.update(updateDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar registro Modulos para tipo de usuario",
            description = "Se asignara un nuevo de registro donde se le asigna un modulo a un tipo de usuario",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Registro creada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }, requestBody =
                    @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            description = "Estructura de datos a guardar", required = true,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeUserModuleSaveDto.class)))
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@Validated @NotNull @RequestBody TypeUserModuleSaveDto saveDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Datos guardado correctamente")
                        .data(this.serviceTypeUserModule.save(saveDto))
                        .build()
        );
    }

    @Operation(
    summary = "Guardar todos los datos relacionados a privilegios de tipo de usuario sobre modulo",
    description = "Se podra enviar los datos de modulo, privilegios y tipo el tipo de usuario para guardarlo todo en una peticion",
    responses = {
            @ApiResponse(responseCode = "201", description = "Todos los registros creada correctamente",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                   @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    },requestBody =
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Estructura de datos a guardar", required = true,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = TypeUserModuleFullSaveDto.class)))
    )
    @PostMapping(path = "/addFull", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> saveModuleToTypeUserFull(@Validated @RequestBody TypeUserModuleFullSaveDto data){
        
        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros guardados ")
                        .data(this.serviceTypeUserModule.addModuleToTypeUserFull(data))
                        .build()); 
    }


    @Operation(summary = "Modulos asignados a tipos de usuario ", description = "Muestra todos los modulos que existen y que tipo de usuario lo puede acceder",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    }
    )
    @GetMapping(path = "/moduleTypeUser", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<ModuleTypeUserGetDto>>> getModuleAndTypeUserDistinct(){

        return ResponseEntity.ok(
                ResponseDTO.<List<ModuleTypeUserGetDto>>builder()
                        .info("Registros obtenidos")
                        .data(this.serviceTypeUserModule.getModuleAndTypeUserDistinct())
                        .build()
        );
    }

    @Operation(summary = "Modulos asignados a tipos de usuario filtrado por ID de tipo de usuario",
            description = "Muestra todos los modulos que han sido asignado a un tipo de usuario y que siga activado",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idTypeUser", in = ParameterIn.PATH, description = "ID de tipo de usuario",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))
    }
    )
    @GetMapping(path = "/moduleTypeUser/{idTypeUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<ModuleOfTypeUserGetDto> >> getModuleDistinctByIdTypeUserAndStatusActived(@NotNull @Min(1) @PathVariable("idTypeUser") Integer idTypeUser){

        return ResponseEntity.ok(
                ResponseDTO.<List<ModuleOfTypeUserGetDto> >builder()
                        .info("Registros obtenidos")
                        .data(this.serviceTypeUserModule.getModuleDistinctByIdTypeUserAndStatusActived(idTypeUser))
                        .build()
        );
    }


    @Operation(summary = "Modulos asignados a tipos de usuario filtrado por ID de tipo de usuario y estado",
            description = "Muestra todos los modulos que han sido asignado a un tipo de usuario pendiendo del estado que se haya pedido",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idTypeUser", in = ParameterIn.PATH, description = "ID de tipo de usuario",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32")),
            @Parameter(name = "idStatus", in = ParameterIn.PATH, description = "ID de estado",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))

    }
    )
    @GetMapping(path = "/moduleTypeUser/{idTypeUser}/status/{idStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<ModuleOfTypeUserGetDto>>> getModuleDistinctByIdTypeUserAndIdStatus(
            @PathVariable(name="idTypeUser") @NotBlank Integer idTypeUser,
            @PathVariable(name = "idStatus") @NotBlank Integer idStatus){

        return ResponseEntity.ok(
                ResponseDTO.<List<ModuleOfTypeUserGetDto>>builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser, idStatus))
                        .build()
        );
    }
    @Operation(summary = "Tipos de usuarios asignados a un modulo segun su estado",
            description = "Muestra los tipos de usuarios que se han asignado a un modulo y que estado tiene esta asignacion o privilegio",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Ids no validos",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idModule", in = ParameterIn.QUERY, description = "ID de module",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64")),
            @Parameter(name = "idStatus", in = ParameterIn.QUERY, description = "ID de status", example = "1", required = true, schema = @Schema(implementation = Integer.class, type = "int", format = "int32", defaultValue = "0"))
    }
    )
    @GetMapping(path = "/typeUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TypeUserOfModuleGetDto>>> getTypeUserDistinctByIdModuleAndIdStatus(
            @NotBlank @RequestParam("idModule") Long idModule,
            @NotBlank @RequestParam("idStatus") Integer idStatus){

        return ResponseEntity.ok(
                ResponseDTO.<List<TypeUserOfModuleGetDto>>builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getTypeUserDistinctByIdModuleAndIdStatus(idModule,idStatus))
                        .build()
        );

    }


    @Operation(summary = "Tipos de usuarios asignacion activada a un modulo",
            description = "Muestra los tipos de usuarios que se han asignado a un modulo y con asignacion o privilegio activado",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no validos",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idModule", in = ParameterIn.PATH, description = "ID de module",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "int", format = "int64"))
    }
    )
    @GetMapping(path = "/typeUser/activated/module/{idModule}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TypeUserOfModuleGetDto>>> getTypeUserDistinctByIdModuleAndStatusActivated(@NotNull @Min(0) @PathVariable("idModule") Long idModule){

        return ResponseEntity.ok(
                ResponseDTO.<List<TypeUserOfModuleGetDto>>builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getTypeUserDistinctByIdModuleAndStatusActivated(idModule))
                        .build()
        );
    }

    @Operation(summary = "Privilegios sobre un modulo asociado a un tipo de usuario",
            description = "Muestra los privilegios y el estado de estos que tiene sobre un modulo un tipo de usuario",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Ids no validos",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idTypeUser", in = ParameterIn.QUERY, description = "ID type user",example = "1",required = true, schema = @Schema(implementation = Integer.class, type = "int", format = "int32")),
            @Parameter(name = "idModule", in = ParameterIn.QUERY, description = "ID de module", example = "1", required = true, schema = @Schema(implementation = Long.class, type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "/privilegeModule", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<PrivilegeOfModuleGetDto>>> getPrivelegeOfModuleByIdTypeUserAndIdModuleAndStatusActived(
            @RequestParam(name = "idTypeUser") @NotBlank Integer idTypeUser,
            @RequestParam(name = "idModule") @NotBlank Long idModule){

        return ResponseEntity.ok(
                ResponseDTO.<List<PrivilegeOfModuleGetDto> >builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getPrivelegeOfModuleByIdTypeUserAndIdModuleAndStatusActived(idTypeUser,idModule))
                        .build()
        );
    }

    @Operation(summary = "Obtencion de informacion de modulo filtrado por tipo de usuario",
            description = "Muestra informacion detallada de cada modulo que fue asignado a un tipo de usuario en especifico",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Registro encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "idTypeUser", in = ParameterIn.PATH, description = "ID de tipo de usuario",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))
    }
    )
    @GetMapping(path = "/typeUser/{idTypeUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TypeUserModuleGetDto>>> getTypeModulePrivilegeByidTypeUserAndStatusActived(@PathVariable(name = "idTypeUser") @Min(1) Integer idTypeUser){

        return ResponseEntity.ok(
                ResponseDTO.<List<TypeUserModuleGetDto>>builder()
                        .info("Registros encontrados")
                        .data(this.serviceTypeUserModule.getTypeModulePrivilegeByidTypeUserAndStatusActived(idTypeUser))
                        .build()
        );
    }
}
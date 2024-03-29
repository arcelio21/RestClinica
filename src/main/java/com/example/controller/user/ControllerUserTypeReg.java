package com.example.controller.user;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.example.dto.user.type_user_reg.TypeUserOfUserRegGetDto;
import com.example.dto.user.type_user_reg.UserRegOfTypeUserGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegGetDto;
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
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<UserTypeRegGetDto>>> getAll() {
        return ResponseEntity.ok(
                ResponseDTO.<List<UserTypeRegGetDto>>builder()
                        .info("Informacion obtenidad")
                        .data(this.service.getAll())
                        .build());
    }

    @Operation(summary = "Obtiene usuario con tipo de usuario asociado filtrado por ID de registro",description = "Cuando se desee un registro especifico se enviada el ID de este registro como parametro",
                method = "GET", responses = {
                @ApiResponse(responseCode = "200",description = "Datos de usuario y su tipo de usuario asociado encontrado",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "404",description = "Data de usuario no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error")))
    },parameters = {
                @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<UserTypeRegGetDto>> getById(@NotNull @Min(1) @PathVariable("id") Long id){
        
        return ResponseEntity.ok(
                ResponseDTO.<UserTypeRegGetDto>builder()
                        .info("Informacion obtenidad")
                        .data(this.service.getById(id))
                        .build());
    }

    @Operation(
    summary = "Guarda registro de usuario con tipo de usuario asociado",
    description = "Este permitira poder asociar un tipo de usuario a un usuario y guardar un registro de esto",
    responses = {
            @ApiResponse(responseCode = "201", description = "registro creada correctamente",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                   @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    },requestBody =
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Estructura de datos a guardar", required = true,
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
                        schema = @Schema(implementation = UserTypeRegSaveDto.class)))
    
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@Validated @RequestBody UserTypeRegSaveDto data){
     
        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros guardados ")
                        .data(this.service.save(data))
                        .build()); 
    }

    @Operation(
        summary = "Actualiza registro de usuario asociado con tipo de usuario",
        description = "Para actualizar la asociacion de un tipo de usuario con un usuario",
        responses = {
                @ApiResponse(responseCode = "200", description = "Registro actualizada correctamente",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                })
        }, requestBody =
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Estructura de datos a actualizar", required = true,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserTypeRegUpdateDto.class)))
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@Validated @RequestBody UserTypeRegUpdateDto dtoUpdate){
     
        return ResponseEntity.ok(
                        ResponseDTO.<Integer>builder()
                                        .info("Cantidad de registros actualizados")
                                        .data(this.service.update(dtoUpdate))
                                        .build()
        );
    }
    
    @Operation(summary = "Filtrar registro por el id de UserReg",description = "Obtiene los tipos de usuario que estan asociados a un usuario",
                method = "GET", responses = {
                @ApiResponse(responseCode = "200",description = "Tipos de usuario para id de usuario encontrado",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "404",description = "data no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error")))
    },parameters = {
                @Parameter(name = "idUserReg", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "/byuserReg/{idUserReg}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TypeUserOfUserRegGetDto>>> getByIdUserReg(@NotNull @Min(1) @PathVariable("idUserReg") Long idUserReLong){
     
        return ResponseEntity.ok(
                ResponseDTO.<List<TypeUserOfUserRegGetDto>>builder()
                        .info("Informacion obtenidad")
                        .data(this.service.getByIdUserReg(idUserReLong))
                        .build());
   }

   @Operation(summary = "Filtrar registro por el id de UserReg y estado activado",description = "Obtiene los tipos de usuario que estan asociados a un usuario y que tiene el registro activado",
                method = "GET", responses = {
                @ApiResponse(responseCode = "200",description = "Tipos de usuario para id de usuario encontrado",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "404",description = "data no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error")))
    },parameters = {
                @Parameter(name = "idUserReg", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "/status/activated/byuserReg/{idUserReg}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TypeUserOfUserRegGetDto>>> getByIdUserRegActivated(@NotNull @Min(1) @PathVariable("idUserReg") Long idUserReg){
     
        return ResponseEntity.ok(
                ResponseDTO.<List<TypeUserOfUserRegGetDto>>builder()
                        .info("Informacion obtenida")
                        .data(this.service.getByIdUserRegActivated(idUserReg))
                        .build());
   }

   @Operation(summary = "Obtiene usuarios que tenga un tipo de usuario especifico",description = "Se podra filtrar los registros para obtener todos los usuarios que han sido asociados a un tipo de usuario especifico", 
                method = "GET", responses = { 
                @ApiResponse(responseCode = "200",description = "Data de usuarios encontrado",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "404",description = "data no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error"))) 
   },parameters = {
                @Parameter(name = "idTypeUser", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))
   }
   )
   @GetMapping(path = "/byTypeUser/{idTypeUser}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ResponseDTO<List<UserRegOfTypeUserGetDto>>> getByIdTypeUser(@NotNull @Min(1) @PathVariable("idTypeUser") Integer idTypeUser){
    
        return ResponseEntity.ok(
                ResponseDTO.<List<UserRegOfTypeUserGetDto>>builder()
                        .info("Informacion obtenidad")
                        .data(this.service.getByIdTypeUser(idTypeUser))
                        .build()); 
   }


   @Operation(summary = "Obtiene registros filtrado por id de estado",description = "Filtrara los registros que se igual al id de estado que se enviara",
                method = "GET", responses = {
                @ApiResponse(responseCode = "200",description = "Registros encontrado",useReturnTypeSchema = true),
                @ApiResponse(responseCode = "404",description = "Registro no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos del error")))
   },parameters = {
                @Parameter(name = "idStatus", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))
   }
   )
   @GetMapping(path = "/byStatus/{idStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ResponseDTO<List<UserTypeRegGetDto>>> getByIdStatus(@NotNull @Min(1) @PathVariable("idStatus") Integer idStatus){
    
        return ResponseEntity.ok(
                ResponseDTO.<List<UserTypeRegGetDto>>builder()
                        .info("Informacion obtenidad")
                        .data(this.service.getByIdStatus(idStatus))
                        .build());
   }
}

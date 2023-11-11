package com.example.controller.user;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.user.user_reg.UserRegDto;
import com.example.dto.user.user_reg.UserRegSaveDto;
import com.example.dto.user.user_reg.UserRegUpdateDto;
import com.example.dto.user.user_reg.UserUpdatePassDto;
import com.example.exception.user.user_reg.UserNotSaveException;
import com.example.service.user.ServiceUserRegImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Controller User", description = "Realiza operaciones sobre los registros disponibles de Usuarios")
@RequestMapping("api/v1/user")
public class ControllerUserReg extends ControllerTemplate {

    private final ServiceUserRegImpl serviceUserReg;

    @Operation(summary = "Obtener todas las usuarios", description = "Se utiliza para obtener todos los usuarios registradas",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<UserRegDto>>> getAll(){

        return ResponseEntity.ok(
                ResponseDTO.<List<UserRegDto>>builder()
                .info("Lista de usuarios registrados")
                .data(this.serviceUserReg.getAll())
                .build()
        );
    }

    @Operation(summary = "Obtener usuario por ID",description = "Se podra obtener un usuario por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Usuario encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Usuario no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<UserRegDto>> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                ResponseDTO.<UserRegDto>builder()
                .info("User Register Found")
                .data(this.serviceUserReg.getById(id))
                .build()
        );
    }

    @Operation(summary = "Obtener usuario por nombre",description = "Se podra obtener un usuario por su Nombre",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Usuarios encontrados",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Usuario no encontrado, nombre no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "name", in = ParameterIn.PATH, description = "nombre de usuario",example = "arcelio",required = true, schema = @Schema(implementation = String.class))
        }
    )
    @GetMapping(path = "/byName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<UserRegDto>>> getByName(@PathVariable("name") String name){

        return ResponseEntity.ok(
                ResponseDTO.<List<UserRegDto>>builder()
                        .info("Usuario filtrados por nombre")
                        .data(this.serviceUserReg.getByName(name))
                        .build()
        );
    }

    @Operation(
            summary = "Actualizar Usuarios",
            description = "Se actualizara usuario que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@RequestBody UserRegUpdateDto userRegDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(this.serviceUserReg.update(userRegDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar nueva usuario",
            description = "Se guardara una nueva usuario en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User creada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save (@RequestBody UserRegSaveDto user) throws UserNotSaveException {


        return new ResponseEntity<>(ResponseDTO.<Integer>builder()
                .info("Cantidad de registro guardados")
                .data(this.serviceUserReg.save(user))
                .build(),
                HttpStatus.CREATED
                );
    }


    @Operation(summary = "Actualizar contraseña de usuario",description = "Se encargara de actualizar contraseña de usuario cuando se desee",
            method = "POST", responses = {
            @ApiResponse(responseCode = "200",description = "Usuario Actualizado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "403",description = "Datos de usuario no valido",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    })
    @PutMapping(path = "/update/password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> updatePassword( @RequestBody UserUpdatePassDto user){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(this.serviceUserReg.updatePassword(user))
                        .build()
        );
    }
}

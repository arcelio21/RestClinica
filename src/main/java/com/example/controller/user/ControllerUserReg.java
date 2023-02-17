package com.example.controller.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.user.UserRegDto;
import com.example.dto.user.UserRegSaveDto;
import com.example.exception.user.UserNotSaveException;
import com.example.service.user.ServiceUserRegImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Controller User", description = "Realiza operaciones sobre los registros disponibles de Usuarios")
@RequestMapping("api/v1/user")
public class ControllerUserReg {

    private final ServiceUserRegImpl serviceUserReg;

    @Operation(summary = "Obtener todas las usuarios", description = "Se utiliza para obtener todos los usuarios registradas",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true,
                    content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){

        return ResponseEntity.ok(
                new ResponseDTO("Lista de usuarios registrados",
                        this.serviceUserReg.getAll())
        );
    }

    @Operation(summary = "Obtener usuario por ID",description = "Se podra obtener un usuario por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Usuario encontrada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRegDto.class,description = "Datos de Address"))),
            @ApiResponse(responseCode = "404",description = "Usuario no encontrada, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserRegDto> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                this.serviceUserReg.getById(id)
        );
    }

    @Operation(
            summary = "Actualizar Usuarios",
            description = "Se actualizara usuario que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizada correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody UserRegDto userRegDto){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Cantidad de registros actualizados")
                        .data(this.serviceUserReg.update(userRegDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar nueva usuario",
            description = "Se guardara una nueva usuario en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User creada correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> save (@RequestBody UserRegSaveDto user) throws UserNotSaveException {


        return new ResponseEntity<>(ResponseDTO.builder()
                .info("Cantidad de registro guardados")
                .data(this.serviceUserReg.save(user))
                .build(),
                HttpStatus.CREATED
                );
    }


    @Operation(summary = "Validar usuario",description = "Se valida el usuario para saber su existencia con contraseña y cedula, principalmente para obtener informacion",
                method = "POST", responses = {
                @ApiResponse(responseCode = "200",description = "Usuario validado",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRegDto.class,description = "Datos de usuarios"))),
                @ApiResponse(responseCode = "403",description = "Datos de usuario no valido",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
        })
    @PostMapping("/Validate")
    public ResponseEntity<UserRegDto> validateAcount(@RequestBody AuthenticationRequest auth){


        return ResponseEntity.ok(
                this.serviceUserReg.validateAccount(auth)
        );
    }
}

package com.example.controller.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.address.AddressGetDto;
import com.example.dto.user.UserRegDto;
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

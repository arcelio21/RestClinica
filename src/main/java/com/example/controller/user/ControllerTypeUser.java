package com.example.controller.user;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.ValidateGroupA;
import com.example.dto.user.type_user.TypeUserDto;
import com.example.dto.user.user_reg.UserRegDto;
import com.example.service.user.ServiceTypeUserImpl;
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
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/typeuser")
@RequiredArgsConstructor
@Tag(name = "Controller Type User", description = "Realizar operaciones con los tipos de usuarios")
public class ControllerTypeUser extends ControllerTemplate {

    private final ServiceTypeUserImpl serviceTypeUser;

    @Operation(summary = "Obtener todas los tipos de  usuarios", description = "Se utiliza para obtener todos los tipos de usuarios registrados",
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
        return ResponseEntity.ok(
          ResponseDTO.builder()
                  .info("Tipos de usuario disponibles")
                  .data(this.serviceTypeUser.getAll())
                  .build()
        );
    }

    @Operation(summary = "Obtener tipo de usuario por ID",description = "Se podra obtener un tipo de usuario por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Tipo de Usuario encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRegDto.class,description = "Datos de Tipo de usuario"))),
            @ApiResponse(responseCode = "404",description = "Tipo de Usuario no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TypeUserDto> getById(@NotNull @Min(1) @PathVariable("id") Integer id){

        return ResponseEntity.ok(
                this.serviceTypeUser.getById(id)
        );
    }


}

package com.example.controller.user;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.user.type_user.TypeUserDto;
import com.example.dto.user.type_user.TypeUserPostDto;
import com.example.dto.user.type_user.TypeUserUpdateDto;
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

import java.util.List;

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
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<TypeUserDto>>> getAll(){
        return ResponseEntity.ok(
          ResponseDTO.<List<TypeUserDto>>builder()
                  .info("Tipos de usuario disponibles")
                  .data(this.serviceTypeUser.getAll())
                  .build()
        );
    }

    @Operation(summary = "Obtener tipo de usuario por ID",description = "Se podra obtener un tipo de usuario por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Tipo de Usuario encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Tipo de Usuario no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Long.class,type = "long", format = "int64"))
    }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<TypeUserDto>> getById(@NotNull @Min(1) @PathVariable("id") Integer id){

        return ResponseEntity.ok(
                ResponseDTO.<TypeUserDto>builder()
                .info("TypeUser Found")
                .data(this.serviceTypeUser.getById(id))
                .build()
        );
    }

    @Operation(
            summary = "Guardar nueva tipo de usuario",
            description = "Se guardara una nuevo tipo de usuario en caso de necesitarse, el ID no es necesario que se escriba ya que no se utilizara durante el guardado de la info",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tipo de usuario creada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = TypeUserPostDto.class)
                                        )
                        )
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@Validated @NotNull @RequestBody TypeUserPostDto typeUserPostDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros guardados")
                        .data(this.serviceTypeUser.save(typeUserPostDto))
                        .build()
        );
    }

    @Operation(
            summary = "Actualizar Tipos de Usuarios",
            description = "Se actualizara tipo de usuario que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tipo Usuario actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = TypeUserUpdateDto.class)
                                        )
                        )
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@Validated @NotNull @RequestBody TypeUserUpdateDto typeUserUpdateDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(this.serviceTypeUser.update(typeUserUpdateDto))
                        .build()
        );
    }


}

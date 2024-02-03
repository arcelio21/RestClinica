package com.example.controller.speciality;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.speciality.userspeciality.UserSpecialityGetDto;
import com.example.dto.speciality.userspeciality.UserSpecialitySaveDto;
import com.example.dto.speciality.userspeciality.UserSpecialityUpdateDto;
import com.example.service.speciality.ServiceUserSpeciality;
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

import java.util.List;

@Tag(name = "Controller User Speciality", description = "Realiza operaciones en datos de usuarios relacionados a especialidades")
@RestController
@RequestMapping("/api/v1/userspeciality")
@RequiredArgsConstructor
public class ControllerUserSpeciality extends ControllerTemplate{

    private final ServiceUserSpeciality service;

    @Operation(summary = "Obtener todas las especialidades de usuarios", description = "Se utiliza para obtener todas las speciality asociadas a usuarios",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<UserSpecialityGetDto>>> getAll(){

        return ResponseEntity.ok(
                ResponseDTO.<List<UserSpecialityGetDto>>builder()
                        .info("Lista de especialidades de usuarios registradas")
                        .data(this.service.getAll())
                        .build()
        );
    }

    @Operation(summary = "Obtener userspeciality por ID",description = "Se podra obtener un usuario asociado a especialidad por ID de registro",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "UserSpeciality encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "UserSpeciality no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de speciality",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))
    }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<UserSpecialityGetDto>> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(
                ResponseDTO.<UserSpecialityGetDto>builder()
                        .info("Speciality Found")
                        .data(this.service.getById(id))
                        .build()
        );
    }

    @Operation(
            summary = "Actualizar UserSpeciality",
            description = "Se actualizara usuario especialidad que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "UserSpeciality actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@RequestBody UserSpecialityUpdateDto userSpecialityUpdateDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(this.service.update(userSpecialityUpdateDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar nueva UserSpeciality",
            description = "Se guardara una nueva usuario especialidad",
            responses = {
                    @ApiResponse(responseCode = "201", description = "UserSpeciality creada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save (@RequestBody UserSpecialitySaveDto userSpecialitySaveDto){


        return new ResponseEntity<>(ResponseDTO.<Integer>builder()
                .info("Cantidad de registro guardados")
                .data(this.service.save(userSpecialitySaveDto))
                .build(),
                HttpStatus.CREATED
        );
    }

}

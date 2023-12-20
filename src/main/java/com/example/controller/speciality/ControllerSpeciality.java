package com.example.controller.speciality;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.speciality.speciality.SpecialityGetDto;
import com.example.dto.speciality.speciality.SpecialitySaveDto;
import com.example.dto.speciality.speciality.SpecialityUpdateDto;
import com.example.service.speciality.ServiceSpecialityImpl;
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

@Tag(name = "Controller Speciality", description = "Realiza peticiones para manipular datos de speciality")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/speciality")
public class ControllerSpeciality extends ControllerTemplate {

    private final ServiceSpecialityImpl serviceSpeciality;

    @Operation(summary = "Obtener todas las especialidades", description = "Se utiliza para obtener todos las speciality registradas",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<SpecialityGetDto>>> getAll(){

        return ResponseEntity.ok(
                ResponseDTO.<List<SpecialityGetDto>>builder()
                        .info("Lista de especialidades registradas")
                        .data(this.serviceSpeciality.getAll())
                        .build()
        );
    }

    @Operation(summary = "Obtener speciality por ID",description = "Se podra obtener una especialidad por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Speciality encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Speciality no encontrado, Id no valido",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de speciality",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "int", format = "int32"))
    }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ResponseDTO<SpecialityGetDto>> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(
                ResponseDTO.<SpecialityGetDto>builder()
                        .info("Speciality Found")
                        .data(this.serviceSpeciality.getById(id))
                        .build()
        );
    }


    @Operation(
            summary = "Actualizar Speciality",
            description = "Se actualizara especialidad que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Speciality actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@RequestBody SpecialityUpdateDto specialityUpdateDto){

        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(this.serviceSpeciality.update(specialityUpdateDto))
                        .build()
        );
    }

    @Operation(
            summary = "Guardar nueva Speciality",
            description = "Se guardara una nueva especialidad en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Speciality creada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    })
            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save (@RequestBody SpecialitySaveDto specialitySaveDto){


        return new ResponseEntity<>(ResponseDTO.<Integer>builder()
                .info("Cantidad de registro guardados")
                .data(this.serviceSpeciality.save(specialitySaveDto))
                .build(),
                HttpStatus.CREATED
        );
    }

}

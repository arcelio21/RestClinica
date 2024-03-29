package com.example.controller.address;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.dto.address.province.ProvinceDto;
import com.example.dto.address.province.ProvinceSaveDto;
import com.example.dto.address.province.ProvinceUpdateDto;
import com.example.service.address.ServiceProvinceImpl;
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
@RequestMapping("/api/v1/province")
@RequiredArgsConstructor
@Tag(name = "Controller Province", description = "Obtener toda la informacion y realizar operaciones relacionada con las provincias")
public class ControllerProvince extends ControllerTemplate {

    private final ServiceProvinceImpl serviceProvince;


    @Operation(summary = "Obtener todas las provincias", description = "Se utiliza para obtener todas las provincias registradas",
        method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<ProvinceDto>>> getAll(){

        ResponseDTO<List<ProvinceDto>> responseDTO = ResponseDTO.<List<ProvinceDto>>builder()
                .data(this.serviceProvince.getAll())
                .info("Datos encontrados")
                .build();
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Obtener provincias por ID",description = "Se podra obtener la provincia que se desee filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Provincia encontrada",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Provincia no encontrada, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<ResponseDTO<ProvinceDto>> getById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(
                ResponseDTO.<ProvinceDto>builder()
                .info("Province Found")
                .data(this.serviceProvince.getById(id))
                .build()
        );
    }

    @Operation(
            summary = "Guardar nueva provincia",
            description = "Se guardara una nueva provincia en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Provincia creada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ProvinceSaveDto.class)
                                        )
                        )
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@RequestBody ProvinceSaveDto provinceSaveDto){

        Integer row = this.serviceProvince.save(provinceSaveDto);


        return  new ResponseEntity<>(ResponseDTO.<Integer>builder()
                .info("Cantidad de registros creados")
                .data(row)
                .build(), HttpStatus.CREATED);

    }

    @Operation(
            summary = "Actualizar provincia",
            description = "Se actualizara provincia que se desee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Provincia actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ProvinceUpdateDto.class)
                                        )
                        )
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@RequestBody ProvinceUpdateDto provinceUpdateDto){

        Integer row = this.serviceProvince.update(provinceUpdateDto);
        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(row)
                        .build()
        );
    }
}

package com.example.controller.address;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.dto.address.district.DistrictAllDto;
import com.example.dto.address.district.DistrictDto;
import com.example.dto.address.district.DistrictSaveDto;
import com.example.dto.address.district.DistrictUpdateDto;
import com.example.service.address.ServiceDistrictImpl;
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
@RequestMapping(value = "/api/v1/district")
@RequiredArgsConstructor
@Tag(name = "Controller District", description = "Se podra realizar todas las operaciones disponibles para district")
public class ControllerDistrict extends ControllerTemplate {

    private final ServiceDistrictImpl serviceDistrict;

    @Operation(summary = "Obtener todas los distritos", description = "Se utiliza para obtener todas los distritos registrados",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<DistrictDto>>> getAll(){

        return ResponseEntity.ok(
                ResponseDTO.<List<DistrictDto>>builder()
                        .info("Distritos disponibles con estrucutura basica")
                        .data(this.serviceDistrict.getAll())
                        .build()
        );
    }

    @Operation(summary = "Obtener distrito por ID",description = "Se podra obtener el distrito que se desee filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Distrito encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Distrito no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<DistrictDto>> getById(@PathVariable("id") Integer id){

        return ResponseEntity.ok(
                ResponseDTO.<DistrictDto>builder()
                .info("District Found")
                .data(this.serviceDistrict.getById(id))
                .build()
        );
    }

    @Operation(
            summary = "Guardar nuevo distrito",
            description = "Se guardara una nuevo distrito en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "distrito creado correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = DistrictSaveDto.class)
                                        )
                        )
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@RequestBody DistrictSaveDto districtDto){
        Integer row = this.serviceDistrict.save(districtDto);
        return  new ResponseEntity<>(ResponseDTO.<Integer>builder()
                .info("Cantidad de registros creados")
                .data(row)
                .build(), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar distrito",
            description = "Se actualizara distrito que se desee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Distrito actualizado correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = DistrictUpdateDto.class)
                                        )
                        )
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@RequestBody DistrictUpdateDto districtDto){
        Integer row = this.serviceDistrict.update(districtDto);
        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(row)
                        .build()
        );
    }

    @Operation(summary = "Obtener distrito basico por ID",description = "Se podra obtener el distrito con su nombre y ID, filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Distrito encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Distrito no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping(path = "/getIdName/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<DistrictDto>> getByIdName(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(
                ResponseDTO.<DistrictDto>builder()
                .info("District Found")
                .data(this.serviceDistrict.getByIdName(id))
                .build()
        );
    }

    @Operation(summary = "Obtener todas los distritos", description = "Se utiliza para obtener todas los distritos registrados con datos basicos de nombre y ID",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(path = "/allIdName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<DistrictDto>>> getAllIdName(){

        return ResponseEntity.ok(
                ResponseDTO.<List<DistrictDto>>builder()
                        .info("Distritos disponibles con estrucutura simple")
                        .data(this.serviceDistrict.getAllIdName())
                        .build()
        );
    }

    @Operation(summary = "Obtener distrito relacionado con una provincia",description = "Se podra obtener el distrito relacionada con la provincia que se busca",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Distrito encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Distrito no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping(path = "/byprovince/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<DistrictDto>>> getByProvince(@PathVariable("id") Integer id){
        return ResponseEntity.ok(
                ResponseDTO.<List<DistrictDto>>builder()
                        .info("Distritos relacionados con la provincia")
                        .data(this.serviceDistrict.getByProvinceId(id))
                        .build()
        );
    }


    @Operation(summary = "Obtener distrito por id con datos de provincia",description = "Se podra obtener el distrito que ademas de los datos principales tendra los datos de la provincia a la que esta relacionado",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Distrito encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Distrito no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping(path = "/districtAndProvince/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<DistrictAllDto>> getDistrictAndProvinceById(@PathVariable("id")  Integer id) {
        return ResponseEntity.ok(
                ResponseDTO.<DistrictAllDto>builder()
                .info("District Found")
                .data(this.serviceDistrict.getDistrictAndProvinceById(id))
                .build()
        );
    }

}

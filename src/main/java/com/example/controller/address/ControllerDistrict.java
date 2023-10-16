package com.example.controller.address;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.dto.address.district.DistrictAllDto;
import com.example.dto.address.district.DistrictDto;
import com.example.dto.address.district.DistrictSaveDto;
import com.example.dto.address.district.DistrictUpdateDto;
import com.example.dto.user.type_user.TypeUserPostDto;
import com.example.service.address.ServiceDistrictImpl;
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
@RequestMapping(value = "/api/v1/district")
@RequiredArgsConstructor
@Tag(name = "Controller District", description = "Se podra realizar todas las operaciones disponibles para district")
public class ControllerDistrict extends ControllerTemplate {

    private final ServiceDistrictImpl serviceDistrict;

    @Operation(summary = "Obtener todas los distritos", description = "Se utiliza para obtener todas los distritos registrados",
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
                ResponseDTO.builder()
                        .info("Distritos disponibles con estrucutura basica")
                        .data(this.serviceDistrict.getAll())
                        .build()
        );
    }

    @Operation(summary = "Obtener distrito por ID",description = "Se podra obtener el distrito que se desee filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Distrito encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DistrictDto.class,description = "Datos de Distrito"))),
            @ApiResponse(responseCode = "404",description = "Distrito no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DistrictDto> getById(@PathVariable("id") Integer id){

        return ResponseEntity.ok(
                this.serviceDistrict.getById(id)
        );
    }

    @Operation(
            summary = "Guardar nuevo distrito",
            description = "Se guardara una nuevo distrito en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "distrito creado correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
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
    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody DistrictSaveDto districtDto){
        Integer row = this.serviceDistrict.save(districtDto);
        return  new ResponseEntity<>(ResponseDTO.builder()
                .info("Cantidad de registros creados")
                .data(row)
                .build(), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar distrito",
            description = "Se actualizara distrito que se desee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Distrito actualizado correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
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
    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody DistrictUpdateDto districtDto){
        Integer row = this.serviceDistrict.update(districtDto);
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Cantidad de registros actualizados")
                        .data(row)
                        .build()
        );
    }

    @Operation(summary = "Obtener distrito basico por ID",description = "Se podra obtener el distrito con su nombre y ID, filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Distrito encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DistrictDto.class,description = "Datos de Distrito"))),
            @ApiResponse(responseCode = "404",description = "Distrito no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping("/getIdName/{id}")
    public ResponseEntity<DistrictDto> getByIdName(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(
                this.serviceDistrict.getByIdName(id)
        );
    }

    @Operation(summary = "Obtener todas los distritos", description = "Se utiliza para obtener todas los distritos registrados con datos basicos de nombre y ID",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true,
                    content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping("/allIdName")
    public ResponseEntity<ResponseDTO> getAllIdName(){

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Distritos disponibles con estrucutura simple")
                        .data(this.serviceDistrict.getAllIdName())
                        .build()
        );
    }

    @Operation(summary = "Obtener distrito relacionado con una provincia",description = "Se podra obtener el distrito relacionada con la provincia que se busca",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Distrito encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDTO.class,description = "Datos de Distrito"))),
            @ApiResponse(responseCode = "404",description = "Distrito no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping("/byprovince/{id}")
    public ResponseEntity<ResponseDTO> getByProvince(@PathVariable("id") Integer id){
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Distritos relacionados con la provincia")
                        .data(this.serviceDistrict.getByProvinceId(id))
                        .build()
        );
    }


    @Operation(summary = "Obtener distrito por id con datos de provincia",description = "Se podra obtener el distrito que ademas de los datos principales tendra los datos de la provincia a la que esta relacionado",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Distrito encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DistrictAllDto.class,description = "Datos de Distrito"))),
            @ApiResponse(responseCode = "404",description = "Distrito no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping("/districtAndProvince/{id}")
    public ResponseEntity<DistrictAllDto> getDistrictAndProvinceById(@PathVariable("id")  Integer id) {
        return ResponseEntity.ok(
                this.serviceDistrict.getDistrictAndProvinceById(id)
        );
    }

}

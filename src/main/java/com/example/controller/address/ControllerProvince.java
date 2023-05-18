package com.example.controller.address;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.dto.address.province.ProvinceDto;
import com.example.service.address.ServiceProvinceImpl;
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
@RequestMapping("/api/v1/province")
@RequiredArgsConstructor
@Tag(name = "Controller Province", description = "Obtener toda la informacion y realizar operaciones relacionada con las provincias")
public class ControllerProvince extends ControllerTemplate {

    private final ServiceProvinceImpl serviceProvince;


    @Operation(summary = "Obtener todas las provincias", description = "Se utiliza para obtener todas las provincias registradas",
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

        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(this.serviceProvince.getAll())
                .info("Datos encontrados")
                .build();
        return ResponseEntity.ok(responseDTO);
    }

    // TODO probar el usereturntypeshcema en false haber si usa la configuracion que le pongo
    @Operation(summary = "Obtener provincias por ID",description = "Se podra obtener la provincia que se desee filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Provincia encontrada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProvinceDto.class,description = "Datos de provincia"))),
            @ApiResponse(responseCode = "404",description = "Provincia no encontrada, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping("/{id}")
    public  ResponseEntity<ProvinceDto> getById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(this.serviceProvince.getById(id));
    }

    @Operation(
            summary = "Guardar nueva provincia",
            description = "Se guardara una nueva provincia en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Provincia creada correctamente",content = {
                           @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                   schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody ProvinceDto provinceDto){

        Integer row = this.serviceProvince.save(provinceDto);


        return  new ResponseEntity<>(ResponseDTO.builder()
                .info("Cantidad de registros creados")
                .data(row)
                .build(), HttpStatus.CREATED);

    }

    @Operation(
            summary = "Actualizar provincia",
            description = "Se actualizara provincia que se desee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Provincia actualizada correctamente",content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            }
    )
    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody ProvinceDto provinceDto){

        Integer row = this.serviceProvince.update(provinceDto);
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .info("Cantidad de registros actualizados")
                        .data(row)
                        .build()
        );
    }
}

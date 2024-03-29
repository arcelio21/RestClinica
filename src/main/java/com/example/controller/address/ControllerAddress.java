package com.example.controller.address;

import com.example.controller.ControllerTemplate;
import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressSaveDto;
import com.example.dto.address.AddressUpdatetDto;
import com.example.dto.address.village.VillagePostDto;
import com.example.service.address.IServiceAddress;
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
@RequiredArgsConstructor
@Tag(name = "Controller Adress", description = "Realiza operaciones sobre los registros disponibles de Address")
@RequestMapping("api/v1/address")
public class ControllerAddress extends ControllerTemplate {

    private final IServiceAddress serviceAddress;


    @Operation(summary = "Obtener todas las direcciones", description = "Se utiliza para obtener todas las direcciones registradas",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<AddressGetDto>>> getAll(){

        ResponseDTO<List<AddressGetDto>> responseDTO = ResponseDTO.<List<AddressGetDto>>builder()
                .data(this.serviceAddress.getAll())
                .info("Datos encontrados")
                .build();
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Obtener address por ID",description = "Se podra obtener la address que se desee filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Address encontrada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AddressGetDto.class,description = "Datos de Address"))),
            @ApiResponse(responseCode = "404",description = "Address no encontrada, Id no valido",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class, description = "Datos de error")))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping("/{id}")
    public  ResponseEntity<AddressGetDto> getById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(this.serviceAddress.getById(id));
    }

    @Operation(
            summary = "Guardar nueva address",
            description = "Se guardara una nueva address en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Address creada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = VillagePostDto.class)
                                        )
                        )
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> save(@RequestBody AddressSaveDto addressSaveDto){

        Integer row = this.serviceAddress.save(addressSaveDto);


        return  new ResponseEntity<>(ResponseDTO.<Integer>builder()
                .info("Cantidad de registros creados")
                .data(row)
                .build(), HttpStatus.CREATED);

    }

    @Operation(
            summary = "Actualizar address",
            description = "Se actualizara address que se desee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Address actualizada correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = AddressUpdatetDto.class)
                                        )
                        )
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@RequestBody AddressUpdatetDto addressUpdatetDto){

        Integer row = this.serviceAddress.update(addressUpdatetDto);
        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(row)
                        .build()
        );
    }
}

package com.example.controller.address;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.dto.address.village.VillageDistrictDto;
import com.example.dto.address.village.VillageDto;
import com.example.dto.address.village.VillagePostDto;
import com.example.dto.address.village.VillageUpdateDto;
import com.example.service.address.ServiceVillageImpl;
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
@RequestMapping("/api/v1/village")
@RequiredArgsConstructor
@Tag(name = "Controller Village", description = "Realizar operaciones diponibles sobre Villages")
public class ControllerVillage extends ControllerTemplate {

    private final ServiceVillageImpl serviceVillage;

    @Operation(summary = "Obtener todas los villages", description = "Se utiliza para obtener todos los villages registrados",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<VillageDto>>> getAll(){

        return ResponseEntity.ok(
                ResponseDTO.<List<VillageDto>>builder()
                .info("List of Villages")
                .data(this.serviceVillage.getAll())
                .build()
        );
    }

    @Operation(summary = "Obtener village por ID",description = "Se podra obtener el village que se desee filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Village encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Village no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<VillageDto>> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(
                ResponseDTO.<VillageDto>builder()
                .info("Village Found")
                .data(this.serviceVillage.getById(id))
                .build()
        );
    }

    @Operation(
            summary = "Guardar nuevo village",
            description = "Se guardara una nuevo village en caso de necesitarse",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Village creado correctamente",useReturnTypeSchema = true),
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
    public ResponseEntity<ResponseDTO<Integer>> save(@RequestBody VillagePostDto villageDto){
        return  new ResponseEntity<>(ResponseDTO.<Integer>builder()
                .info("Cantidad de registros creados")
                .data(this.serviceVillage.save(villageDto))
                .build(), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar village",
            description = "Se actualizara village que se desee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Village actualizado correctamente",useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Datos proporcionado no son validos",content = {
                            @Content(schema = @Schema)
                    })
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = VillageUpdateDto.class)
                                        )
                        )
    )
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<Integer>> update(@RequestBody VillageUpdateDto villageDto){
        return ResponseEntity.ok(
                ResponseDTO.<Integer>builder()
                        .info("Cantidad de registros actualizados")
                        .data(this.serviceVillage.update(villageDto))
                        .build()
        );

    }

    @Operation(summary = "Obtener village relacionado con un distrito",description = "Se podra obtener el village relacionada con el distrito que se busca",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Village encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Village no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping(path = "/bydistrict/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<VillageDto>>> getByDistrictId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(
                ResponseDTO.<List<VillageDto>>builder()
                        .info("Villages relacionados al distritos buscado")
                        .data(this.serviceVillage.getByDistrictId(id))
                        .build()
        );
    }


    @Operation(summary = "Obtener village por id con datos de distrito",description = "Se podra obtener el village que ademas de los datos principales tendra los datos del distrito a la que esta relacionado",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Village encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Village no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping(path = "/villageanddistrict/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<VillageDistrictDto>> getDistrictAllById(@PathVariable("id") Integer id){

        return ResponseEntity.ok(
                ResponseDTO.<VillageDistrictDto>builder()
                .info("Información encontrada")
                .data(this.serviceVillage.getDistrictAllById(id))
                .build()
        );
    }

    @Operation(summary = "Obtener todas los villages con datos basico", description = "Se utiliza para obtener todas los villages registrados con solo ID, NAME",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema)
            })
    }
    )
    @GetMapping(path = "/allIdName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<VillageDto>>> getAllIdName(){
        return ResponseEntity.ok(
                ResponseDTO.<List<VillageDto>>builder()
                        .info("Informacion basica de village")
                        .data(this.serviceVillage.getAllIdName())
                        .build()
        );
    }

    @Operation(summary = "Obtener village basico por ID",description = "Se podra obtener el village con su nombre y ID, filtrandolo por su ID",
            method = "GET", responses = {
            @ApiResponse(responseCode = "200",description = "Village encontrado",useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404",description = "Village no encontrado, Id no valido",content = @Content(schema = @Schema))
    },parameters = {
            @Parameter(name = "id", in = ParameterIn.PATH, description = "ID de recurso",example = "1",required = true, schema = @Schema(implementation = Integer.class,type = "integer", format = "int32"))
    }
    )
    @GetMapping(path = "/getIdName/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<ResponseDTO<VillageDto>> getByIdName(@PathVariable("id") Integer id){

        return ResponseEntity.ok(
                ResponseDTO.<VillageDto>builder()
                .info("null")
                .data(this.serviceVillage.getByIdName(id))
                .build()
        );
    }


}

package com.example.controller.address;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.service.address.ServiceProvinceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/province")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Controller Province", description = "Obtener toda la informacion y realizar operaciones relacionada con las provincias")
public class ControllerProvince extends ControllerTemplate {

    private final ServiceProvinceImpl serviceProvince;


    @Operation(summary = "Obtener todas las provincias", description = "Se utiliza para obtener todas las provincias registradas",
        method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true,
                    content =@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class)))
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
}

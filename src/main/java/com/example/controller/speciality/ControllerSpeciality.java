package com.example.controller.speciality;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.dto.speciality.speciality.SpecialityGetDto;
import com.example.dto.user.user_reg.UserRegDto;
import com.example.service.speciality.ServiceSpecialityImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

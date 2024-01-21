package com.example.controller.speciality;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.dto.speciality.speciality.SpecialityGetDto;
import com.example.dto.speciality.userspeciality.UserSpecialityGetDto;
import com.example.service.speciality.ServiceUserSpeciality;
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

@Tag(name = "Controller User Speciality", description = "Realiza operaciones en datos de usuarios relacionados a especialidades")
@RestController
@RequestMapping("/api/v1/userspeciality")
@RequiredArgsConstructor
public class ControllerUserSpeciality extends ControllerTemplate{

    private ServiceUserSpeciality service;

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

}

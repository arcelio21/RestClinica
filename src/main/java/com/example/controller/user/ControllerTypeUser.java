package com.example.controller.user;

import com.example.dto.ErrorResponseDto;
import com.example.dto.ResponseDTO;
import com.example.service.user.ServiceTypeUserImpl;
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

@RestController
@RequestMapping("/api/v1/typeuser")
@RequiredArgsConstructor
@Tag(name = "Controller Type User", description = "Realizar operaciones con los tipos de usuarios")
public class ControllerTypeUser {

    private final ServiceTypeUserImpl serviceTypeUser;

    @Operation(summary = "Obtener todas los tipos de  usuarios", description = "Se utiliza para obtener todos los tipos de usuarios registrados",
            method = "Get",responses = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa",useReturnTypeSchema = true,
                    content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            })
    }
    )
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        return ResponseEntity.ok(
          ResponseDTO.builder()
                  .info("Tipos de usuario disponibles")
                  .data(this.serviceTypeUser.getAll())
                  .build()
        );
    }
}

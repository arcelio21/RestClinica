package com.example.controller.module;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.ControllerTemplate;
import com.example.dto.ResponseDTO;
import com.example.service.modules.ServiceModuleImple;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/module")
public class Controller extends ControllerTemplate{
    

    private ServiceModuleImple serviceModuleImple;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        
        return ResponseEntity.ok(
            ResponseDTO.builder()
                .info("Datos disponibles")
                .data(this.serviceModuleImple.getAll())
                .build()
        );
        
    }
}

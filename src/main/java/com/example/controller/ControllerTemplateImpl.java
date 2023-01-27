package com.example.controller;

import java.util.Collections;
import java.util.List;

import com.example.dto.RequestResponseDTO;
import com.example.service.ServiceTemplateCrud;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ControllerTemplateImpl<T, S extends ServiceTemplateCrud<T, Integer>>
		implements IControllerTemplate<T>{

	protected S service;

	protected ControllerTemplateImpl(S service) {
		this.service = service;
	}

	@Operation(summary = "Obtener todos los registros disponibles", description = "Se buscara en la base de datos todos estos registro, y deolvera una lista con todos los objetos relacionados a cada registro")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Si encuentra registros disponibles", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Error en peticion", content = @Content(mediaType = MediaType.ALL_VALUE)),
			@ApiResponse(responseCode = "404", description = "Registros no encontrados", content = @Content(mediaType = MediaType.ALL_VALUE))
	})
	@GetMapping
	@Override
	public ResponseEntity<RequestResponseDTO> getAll() {
		log.info("REALIZANDO BUSQUEDA");
		RequestResponseDTO responseDTO = null;
		Object data=null;
		try {
			data = (Object) this.service.getAll();
			responseDTO= RequestResponseDTO.builder()
					.status("Success")
					.description("Datos encontrados")
					.data(data)
					.build();

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} catch (Exception e) {
			data = Collections.emptyList();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<RequestResponseDTO> getById(
			@Parameter(name = "Id register", description = "1") @PathVariable("id") Integer id) {

		RequestResponseDTO responseDTO=null;
		try {
			Object data = (Object) this.service.getById(id);

			if (data == null) {
				responseDTO=RequestResponseDTO.builder()
						.status("Error")
						.description("Dato no encontrado")
						.data(Collections.emptyList())
						.build();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
			} else {
				responseDTO=RequestResponseDTO.builder()
						.status("Success")
						.description("Dato encontrado")
						.data(data)
						.build();
				return ResponseEntity.ok(responseDTO);
			}

		} catch (Exception e) {
			responseDTO=RequestResponseDTO.builder()
					.status("Error")
					.description("Dato no encontrado")
					.data(Collections.emptyList())
					.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
		}
	}

	@PostMapping
	@Override
	public ResponseEntity<RequestResponseDTO> save(@RequestBody T t) {
		System.out.println("Inicio de guardado");
		if (t == null) {
			System.out.println("RETORNAR POR VALOR NULO");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		try {
			Integer rowAffected = this.service.save(t);

			if (rowAffected == 1) {
				System.out.println("RETORNAR POR VALOR ACEPTADO");
				return ResponseEntity.status(HttpStatus.CREATED).body(null);
			} else {
				System.out.println("RETORNAR POR VALOR FALLADO");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		} catch (Exception e) {
			System.out.println("RETORNAR POR VALOR EXCEPTION");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PutMapping
	@Override
	public ResponseEntity<RequestResponseDTO> update(@RequestBody T t) {
		return null;
	}

}

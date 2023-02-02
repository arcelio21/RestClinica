package com.example.dto;

import com.example.dto.address.province.ProvinceDto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ResponseDTO(String info ,
                         Object data) {
}

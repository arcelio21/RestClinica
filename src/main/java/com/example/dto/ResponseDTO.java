package com.example.dto;

import lombok.Builder;

@Builder
public record ResponseDTO(String info ,
                         Object data) {
}

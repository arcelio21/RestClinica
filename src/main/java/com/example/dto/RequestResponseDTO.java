package com.example.dto;

import lombok.Builder;

@Builder
public record RequestResponseDTO(String status, String description, Object data) {
}

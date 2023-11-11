package com.example.dto;

import lombok.Builder;

@Builder
public record ResponseDTO<DATA>(String info ,
                         DATA data) {
}

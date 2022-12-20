package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

	@JsonProperty(value = "token_type")
	private String tokenType;

	@JsonProperty(value = "access_token")
	private String accessToken;

	@JsonProperty(value = "expire_in")
	private int expireIn;

	@JsonProperty(value = "issued_at")
	private String issuedAt;

	@JsonProperty(value = "client_id")
	private String clientId;

}

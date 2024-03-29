package com.example.dto.user.user_reg;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
public class UserRegUpdateDto extends UserRegDto{


    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    private Long addressId;

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    private Long villageId;

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    private String direcSpecific;

    @Builder(builderMethodName = "userUpdateBuilder")
    public UserRegUpdateDto(Long id, Long idenCard, String name, String lastName, String contact, String email,
                          LocalDate birthday, LocalDateTime creationDate, Long addressId,
                          Long villageId, String direcSpecific
    ) {
        super(id, idenCard, name, lastName, null, contact, email, birthday, creationDate, addressId);
        this.direcSpecific=direcSpecific;
        this.villageId=villageId;
        this.addressId = addressId;
    }

}

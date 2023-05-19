package com.example.dto.user.user_reg;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Schema(name = "UserRegSave", description = "Dto utilizado para guardar info de usuario")
public class UserRegSaveDto extends UserRegDto{

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    private Long villageId;

    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    private String direcSpecific;


    @Builder(builderMethodName = "userSaveBUilder")
    public UserRegSaveDto(Long id, Long idenCard, String name, String lastName, String password, String contact, String email,
                          LocalDate birthday, LocalDateTime creationDate, Long addressId,
                          Long villageId, String direcSpecific
    ) {
        super(id, idenCard, name, lastName, password, contact, email, birthday, creationDate, addressId);
        this.villageId= villageId;
        this.direcSpecific=direcSpecific;
    }

}

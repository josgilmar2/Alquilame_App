package com.salesianostriana.dam.alquilame.user.dto;

import com.salesianostriana.dam.alquilame.validation.annotation.general.FieldsValueMatch;
import com.salesianostriana.dam.alquilame.validation.annotation.user.OldPasswordMatch;
import com.salesianostriana.dam.alquilame.validation.annotation.user.StrongPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@OldPasswordMatch(oldPasswordField = "oldPassword", newPasswordField = "newPassword",
        message = "{editPasswordDto.password.nomatch}")
@FieldsValueMatch.List({
        @FieldsValueMatch(field = "newPassword", verifyField = "verifyNewPassword",
                message = "{createUserDto.password.nomatch}")
})
public class EditPasswordDto {

    @NotEmpty(message = "{createUserDto.password.notempty}")
    private String oldPassword;

    @StrongPassword
    @NotEmpty(message = "{createUserDto.password.notempty}")
    private String newPassword;

    @NotEmpty(message = "{createUserDto.password.notempty}")
    private String verifyNewPassword;

}

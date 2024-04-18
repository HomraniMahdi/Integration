package tn.esprit.devminds.Payload;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String confirmationKeyValue;
    private String newPassword;
    private String confirmedNewPassword;
}

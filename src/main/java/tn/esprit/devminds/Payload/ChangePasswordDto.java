package tn.esprit.devminds.Payload;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String newPassword;
    private String confirmation;
}

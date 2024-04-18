package tn.esprit.devminds.Payload;

import lombok.Data;

@Data
public class UpdateProfileDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}

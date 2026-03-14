package halcyon.clemncare.app.dto;

import lombok.Data;

@Data
public class GuardianDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private Long familyId;

}
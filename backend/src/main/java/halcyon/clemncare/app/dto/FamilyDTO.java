package halcyon.clemncare.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class FamilyDTO {
    
    private Long id;
    private List<ChildDTO> children;
    private List<GuardianDTO> guardians;
    private GuardianDTO emergencyContact;
    private HomeAddressDTO address;
}

package halcyon.clemncare.app.mapper;

import halcyon.clemncare.app.dto.GuardianDTO;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import org.springframework.stereotype.Component;

@Component
public class GuardianMapper {

    public GuardianDTO toDTO(Guardian guardian) {
        if (guardian == null) return null;

        GuardianDTO dto = new GuardianDTO();
        dto.setId(guardian.getId());
        dto.setFirstName(guardian.getFirstName());
        dto.setLastName(guardian.getLastName());
        dto.setPhoneNumber(guardian.getPhoneNumber());
        dto.setEmailAddress(guardian.getEmailAddress());
        dto.setFamilyId(guardian.getFamily() != null ? guardian.getFamily().getId() : null);
        dto.setRelationship(guardian.getRelationship());
        dto.setEmergencyContact(guardian.isEmergencyContact());

        return dto;
    }

    public Guardian toEntity(GuardianDTO dto) {
        if (dto == null) return null;

        Guardian guardian = new Guardian();
        guardian.setId(dto.getId());
        guardian.setFirstName(dto.getFirstName());
        guardian.setLastName(dto.getLastName());
        guardian.setPhoneNumber(dto.getPhoneNumber());
        guardian.setEmailAddress(dto.getEmailAddress());
        guardian.setRelationship(dto.getRelationship());
        guardian.setEmergencyContact(dto.isEmergencyContact());

        if (dto.getFamilyId() != null) {
            Family family = new Family();
            family.setId(dto.getFamilyId());
            guardian.setFamily(family);
        }

        return guardian;
    }
}
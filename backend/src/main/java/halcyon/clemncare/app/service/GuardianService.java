package halcyon.clemncare.app.service;

import halcyon.clemncare.app.model.Guardian;

import java.util.List;

public interface GuardianService {

    Guardian createGuardian(Guardian guardian);

    Guardian updateGuardian(Long id, Guardian guardian);

    Guardian getGuardian(Long id);

    List<Guardian> getAllGuardians();

    void deleteGuardian(Long id);

    List<Guardian> getGuardiansByFamilyId(Long familyId);
}
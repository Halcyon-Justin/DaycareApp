package halcyon.clemncare.app.service;

import java.util.List;
import java.util.Optional;

import halcyon.clemncare.app.dto.GuardianDTO;
import halcyon.clemncare.app.model.Guardian;

public interface GuardianService {
    public Guardian createGuardian(GuardianDTO guardianDTO);

    public Guardian updateGuardian(Long id, GuardianDTO guardianDTO);

    public Guardian partialUpdateGuardian(Long id, GuardianDTO guardianDTO);

    public void deleteGuardian(Long guardianId);

    public Optional<Guardian> getGuardian(Long guardianId);

    public List<Guardian> getAllGuardians();
}

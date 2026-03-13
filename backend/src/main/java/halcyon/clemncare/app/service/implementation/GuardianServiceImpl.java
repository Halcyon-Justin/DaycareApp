package halcyon.clemncare.app.service.implementation;

import halcyon.clemncare.app.exception.FamilyNotFoundException;
import halcyon.clemncare.app.exception.GuardianNotFoundException;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianServiceImpl implements GuardianService {

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Guardian createGuardian(Guardian guardian) {
        if (guardian.getFamily() != null && guardian.getFamily().getId() != null) {
            Family family = familyRepository.findById(guardian.getFamily().getId())
                    .orElseThrow(() -> new FamilyNotFoundException(
                            "Family with ID " + guardian.getFamily().getId() + " not found"));
            guardian.setFamily(family);
        }
        return guardianRepository.save(guardian);
    }

    @Override
    public Guardian updateGuardian(Long id, Guardian guardian) {
        Guardian existing = guardianRepository.findById(id)
                .orElseThrow(() -> new GuardianNotFoundException("Guardian with ID " + id + " not found"));

        if (guardian.getFirstName() != null) existing.setFirstName(guardian.getFirstName());
        if (guardian.getLastName() != null) existing.setLastName(guardian.getLastName());
        if (guardian.getPhoneNumber() != null) existing.setPhoneNumber(guardian.getPhoneNumber());
        if (guardian.getEmailAddress() != null) existing.setEmailAddress(guardian.getEmailAddress());
        if (guardian.getRelationship() != null) existing.setRelationship(guardian.getRelationship());
        existing.setEmergencyContact(guardian.isEmergencyContact());

        if (guardian.getFamily() != null && guardian.getFamily().getId() != null) {
            Family family = familyRepository.findById(guardian.getFamily().getId())
                    .orElseThrow(() -> new FamilyNotFoundException(
                            "Family with ID " + guardian.getFamily().getId() + " not found"));
            existing.setFamily(family);
        }

        return guardianRepository.save(existing);
    }

    @Override
    public Guardian getGuardian(Long id) {
        return guardianRepository.findById(id)
                .orElseThrow(() -> new GuardianNotFoundException("Guardian with ID " + id + " not found"));
    }

    @Override
    public List<Guardian> getAllGuardians() {
        return guardianRepository.findAll();
    }

    @Override
    public void deleteGuardian(Long id) {
        if (!guardianRepository.existsById(id)) {
            throw new GuardianNotFoundException("Guardian with ID " + id + " not found");
        }
        guardianRepository.deleteById(id);
    }

    @Override
    public List<Guardian> getGuardiansByFamilyId(Long familyId) {
        return guardianRepository.findByFamilyId(familyId);
    }
}
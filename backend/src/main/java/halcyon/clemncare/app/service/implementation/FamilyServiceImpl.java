package halcyon.clemncare.app.service.implementation;

import halcyon.clemncare.app.exception.FamilyNotFoundException;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Family createFamily(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Family updateFamily(Long id, Family family) {
        Family existing = familyRepository.findById(id)
                .orElseThrow(() -> new FamilyNotFoundException("Family with ID " + id + " not found"));

        if (family.getAddress() != null) existing.setAddress(family.getAddress());
        return familyRepository.save(existing);
    }

    @Override
    public Family getFamily(Long id) {
        return familyRepository.findById(id)
                .orElseThrow(() -> new FamilyNotFoundException("Family with ID " + id + " not found"));
    }

    @Override
    public List<Family> getAllFamilies() {
        return familyRepository.findAll();
    }

    @Override
    public void deleteFamily(Long id) {
        if (!familyRepository.existsById(id)) {
            throw new FamilyNotFoundException("Family with ID " + id + " not found");
        }
        familyRepository.deleteById(id);
    }
}
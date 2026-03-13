package halcyon.clemncare.app.service;

import java.util.List;
import java.util.Optional;

import halcyon.clemncare.app.dto.FamilyDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;

public interface FamilyService {
    public Family createFamily(FamilyDTO familyDTO);

    public void deleteFamily(Long familyId);

    public Optional<Family> getFamily(Long familyId);

    public List<Family> getAllFamilies();

    public List<Child> getActiveChildrenFromFamilyId(Long familyId);
    
}

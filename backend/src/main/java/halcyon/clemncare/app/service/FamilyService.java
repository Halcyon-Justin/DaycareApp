package halcyon.clemncare.app.service;

import halcyon.clemncare.app.model.Family;

import java.util.List;

public interface FamilyService {

    Family createFamily(Family family);

    Family updateFamily(Long id, Family family);

    Family getFamily(Long id);

    List<Family> getAllFamilies();

    void deleteFamily(Long id);
}
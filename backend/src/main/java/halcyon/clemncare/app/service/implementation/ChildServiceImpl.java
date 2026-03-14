package halcyon.clemncare.app.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.enums.EnrollmentStatus;
import halcyon.clemncare.app.exception.ChildNotFoundException;
import halcyon.clemncare.app.exception.FamilyNotFoundException;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.service.ChildService;

@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Child createChild(Child child) {
        // Ensure family exists
        if (child.getFamily() != null && child.getFamily().getId() != null) {
            Optional<Family> familyOptional = familyRepository.findById(child.getFamily().getId());
            if (!familyOptional.isPresent()) {
                throw new FamilyNotFoundException(
                        "Family with ID " + child.getFamily().getId() + " not found");
            }
            child.setFamily(familyOptional.get());
            if (child.getStatus() == null) {
                child.setStatus(EnrollmentStatus.ENROLLED);
            }
        }
        return childRepository.save(child);
    }

    @Override
    public Child updateChild(Long id, Child child) {
        Child existingChild = childRepository.findById(id)
                .orElseThrow(() -> new ChildNotFoundException("Child with ID " + id + " not found"));

        // Update non-null fields
        if (child.getFirstName() != null)
            existingChild.setFirstName(child.getFirstName());
        if (child.getLastName() != null)
            existingChild.setLastName(child.getLastName());
        if (child.getDateOfBirth() != null)
            existingChild.setDateOfBirth(child.getDateOfBirth());
        existingChild.setStatus(child.getStatus());
        if (child.getNotes() != null)
            existingChild.setNotes(child.getNotes());
        if (child.getStatus() != null) {
            existingChild.setStatus(child.getStatus());
        }

        if (child.getFamily() != null && child.getFamily().getId() != null) {
            Family family = familyRepository.findById(child.getFamily().getId())
                    .orElseThrow(() -> new FamilyNotFoundException(
                            "Family with ID " + child.getFamily().getId() + " not found"));
            existingChild.setFamily(family);
        }

        return childRepository.save(existingChild);
    }

    @Override
    public Child partialUpdateChild(Long id, Child child) {
        // Partial update is identical to update here, but could be customized
        return updateChild(id, child);
    }

    @Override
    public void deleteChild(Long childId) {
        if (!childRepository.existsById(childId)) {
            throw new ChildNotFoundException("Child with ID " + childId + " not found. Cannot delete.");
        }
        childRepository.deleteById(childId);
    }

    @Override
    public Child getChild(Long childId) {
        return childRepository.findById(childId).orElse(null);
    }

    @Override
    public Page<Child> getAllChildren(Pageable pageable) {
        return childRepository.findAll(pageable);
    }

    @Override
    public List<Child> findChildrenByAge(int age) {
        List<Child> childrenByAge = childRepository.findAll().stream()
                .filter(child -> child.getAge() == age)
                .collect(Collectors.toList());

        if (childrenByAge.isEmpty()) {
            throw new ChildNotFoundException("No children found for the specified age");
        }
        return childrenByAge;
    }

    @Override
    public List<Child> getEnrolledChildren() {
        return childRepository.findByStatus(EnrollmentStatus.ENROLLED);
    }
}
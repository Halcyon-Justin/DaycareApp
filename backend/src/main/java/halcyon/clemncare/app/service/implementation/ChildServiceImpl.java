package halcyon.clemncare.app.service.implementation;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.ChildDTO;
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
    public Child createChild(ChildDTO childDTO) {
        Optional<Family> familyOptional = familyRepository.findById(childDTO.getFamilyId());
        if (familyOptional.isPresent()) {
            Child child = new Child();

            // Assign values from the DTO to the child
            BeanUtils.copyProperties(childDTO, child);
            child.setFamily(familyOptional.get());

            return childRepository.save(child);
        } else {
            // Handle the case where the associated family does not exist
            throw new FamilyNotFoundException("Family with ID " + childDTO.getFamilyId() + " not found");
        }
    }

    @Override
    public Child updateChild(Long id, ChildDTO childDTO) {
        Optional<Child> optionalChild = childRepository.findById(id);
        if (optionalChild.isPresent()) {
            Child existingChild = optionalChild.get();
            BeanUtils.copyProperties(childDTO, existingChild, getNullPropertyNames(childDTO));
            return childRepository.save(existingChild);
        } else {
            throw new ChildNotFoundException("Child with ID " + id + " not found");
        }
    }

    @Override
    public Child partialUpdateChild(Long id, ChildDTO childDTO) {
        Optional<Child> optionalChild = childRepository.findById(id);
        if (optionalChild.isPresent()) {
            Child existingChild = optionalChild.get();
            BeanUtils.copyProperties(childDTO, existingChild, getNullPropertyNames(childDTO));
            return childRepository.save(existingChild);
        } else {
            throw new ChildNotFoundException("Child with ID " + id + " not found");
        }
    }

    @Override
    public void deleteChild(Long childId) {
        childRepository.deleteById(childId);
    }

    @Override
    public Child getChild(Long childId) {
        return childRepository.findById(childId).orElse(null);
    }

    @Override
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    @Override
    public List<Child> findChildrenByAge(int age) {
        List<Child> allChildren = childRepository.findAll();

        List<Child> childrenByAge = allChildren.stream()
                .filter(child -> child.getAge() == age)
                .collect(Collectors.toList());

        if (childrenByAge.isEmpty()) {
            throw new ChildNotFoundException("No children found for the specified age");
        } else {
            return childrenByAge;
        }
    }

    private String[] getNullPropertyNames(ChildDTO childDTO) {
        final BeanWrapper src = new BeanWrapperImpl(childDTO);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

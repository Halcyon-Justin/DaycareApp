package halcyon.clemncare.app.service.implementation;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.GuardianDTO;
import halcyon.clemncare.app.exception.FamilyNotFoundException;
import halcyon.clemncare.app.exception.GuardianNotFoundException;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.service.GuardianService;

@Service
public class GuardianServiceImpl implements GuardianService {

    @Autowired
    GuardianRepository guardianRepository;

    @Autowired
    FamilyRepository familyRepository;

    @Override
    public Guardian createGuardian(GuardianDTO guardianDTO) {
        Optional<Family> familyOptional = familyRepository.findById(guardianDTO.getFamilyId());
        if (familyOptional.isPresent()) {
            Guardian guardian = new Guardian();

            // Assign values from the DTO to the guardian
            BeanUtils.copyProperties(guardianDTO, guardian);
            guardian.setFamily(familyOptional.get());

            return guardianRepository.save(guardian);
        } else {
            // Handle the case where the associated family does not exist
            throw new FamilyNotFoundException("Family with ID " + guardianDTO.getFamilyId() + " not found");
        }
    }

    @Override
    public Guardian updateGuardian(Long id, GuardianDTO guardianDTO) {
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);
        if (optionalGuardian.isPresent()) {
            Guardian existingGuardian = optionalGuardian.get();
            BeanUtils.copyProperties(guardianDTO, existingGuardian);
            return guardianRepository.save(existingGuardian);
        } else {
            throw new GuardianNotFoundException("Guardian with ID " + id + " not found");
        }
    }

    @Override
    public Guardian partialUpdateGuardian(Long id, GuardianDTO guardianDTO) {
        Optional<Guardian> optionalGuardian = guardianRepository.findById(id);
        if (optionalGuardian.isPresent()) {
            Guardian existingGuardian = optionalGuardian.get();
            BeanUtils.copyProperties(guardianDTO, existingGuardian, getNullPropertyNames(guardianDTO));
            return guardianRepository.save(existingGuardian);
        } else {
            throw new GuardianNotFoundException("Guardian with ID " + id + " not found");
        }
    }

    @Override
    public void deleteGuardian(Long guardianId) {
        guardianRepository.deleteById(guardianId);
    }

    @Override
    public Optional<Guardian> getGuardian(Long guardianId) {
        return guardianRepository.findById(guardianId);
    }

    @Override
    public List<Guardian> getAllGuardians() {
        return guardianRepository.findAll();
    }

    private String[] getNullPropertyNames(GuardianDTO guardianDTO) {
        final BeanWrapper src = new BeanWrapperImpl(guardianDTO);
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

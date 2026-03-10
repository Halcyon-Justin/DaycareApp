package halcyon.clemncare.app.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.ChildDTO;
import halcyon.clemncare.app.dto.FamilyDTO;
import halcyon.clemncare.app.dto.GuardianDTO;
import halcyon.clemncare.app.dto.HomeAddressDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.HomeAddress;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.FamilyRepository;
import halcyon.clemncare.app.repositories.GuardianRepository;
import halcyon.clemncare.app.repositories.HomeAddressRepository;
import halcyon.clemncare.app.service.FamilyService;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private HomeAddressRepository homeAddressRepository;

    @Override
    public Family createFamily(FamilyDTO familyDTO) {
        List<ChildDTO> childrenDTO = familyDTO.getChildren();
        List<GuardianDTO> guardiansDTO = familyDTO.getGuardians();
        GuardianDTO emergencyContactDTO = familyDTO.getEmergencyContact();
        HomeAddressDTO addressDTO = familyDTO.getAddress();

        // creating mappings from each DTO to actual Object
        // Send through saving them invididually,
        List<Child> children = saveChildren(childrenDTO);
        List<Guardian> guardians = saveGuardians(guardiansDTO);
        Guardian emergencyContact = saveEmergencyContact(emergencyContactDTO);
        HomeAddress address = saveAddress(addressDTO);

        // Create new Family
        Family family = new Family();

        // Set the Family to Created Objects
        for (Child child : children) {
            child.setFamily(family);
        }

        for (Guardian guardian : guardians) {
            guardian.setFamily(family);
        }

        emergencyContact.setFamily(family);

        family.setChildren(children);
        family.setGuardians(guardians);
        family.setAddress(address);

        // Save Family
        return familyRepository.save(family);
    }

    @Override
    public void deleteFamily(Long familyId) {
        familyRepository.deleteById(familyId);
    }

    @Override
    public Optional<Family> getFamily(Long familyId) {
        return familyRepository.findById(familyId);
    }

    @Override
    public List<Family> getAllFamilies() {
        return familyRepository.findAll();
    }

    @Override
    public List<Child> getActiveChildrenFromFamilyId(Long familyId) {
        List<Family> families = familyRepository.getAllFamiliesWithActiveChildren();
        for (Family family : families) {
            if (family.getId().equals(familyId)) {
                return family.getActiveChildren();
            }
        }
        return List.of();
    }

    private List<Child> saveChildren(List<ChildDTO> children) {
        List<Child> childrenList = new ArrayList<Child>();
        for (ChildDTO childDTO : children) {
            Child child = new Child();
            child.setId(childDTO.getId());
            child.setFirstName(childDTO.getFirstName());
            child.setLastName(childDTO.getLastName());
            child.setDateOfBirth(childDTO.getDateOfBirth());
            child.setActive(childDTO.isActive());
            child.setNotes(childDTO.getNotes());
            childRepository.save(child);
            childrenList.add(child);
        }
        return childrenList;
    }

    private List<Guardian> saveGuardians(List<GuardianDTO> guardians) {
        List<Guardian> guardianList = new ArrayList<Guardian>();
        for (GuardianDTO guardDTO : guardians) {
            Guardian guardian = new Guardian();
            guardian.setId(guardDTO.getId());
            guardian.setFirstName(guardDTO.getFirstName());
            guardian.setLastName(guardDTO.getLastName());
            guardian.setPhoneNumber(guardDTO.getPhoneNumber());
            guardian.setEmailAddress(guardDTO.getEmailAddress());
            guardian.setRelationship(guardDTO.getRelationship());
            guardianRepository.save(guardian);
            guardianList.add(guardian);
        }
        return guardianList;
    }

    private Guardian saveEmergencyContact(GuardianDTO emergencyContactDTO) {
        Guardian emergencyContact = new Guardian();
        emergencyContact.setId(emergencyContactDTO.getId());
        emergencyContact.setFirstName(emergencyContactDTO.getFirstName());
        emergencyContact.setLastName(emergencyContactDTO.getLastName());
        emergencyContact.setPhoneNumber(emergencyContactDTO.getPhoneNumber());
        emergencyContact.setEmailAddress(emergencyContactDTO.getEmailAddress());
        emergencyContact.setRelationship(emergencyContactDTO.getRelationship());
        emergencyContact.setEmergencyContact(true);
        return guardianRepository.save(emergencyContact);
    }

    public HomeAddress saveAddress(HomeAddressDTO addressDTO) {
        HomeAddress address = new HomeAddress();
        address.setId(addressDTO.getId());
        address.setStreetAddress(addressDTO.getStreetAddress());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        return homeAddressRepository.save(address);
    }

}

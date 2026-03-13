package halcyon.clemncare.app.mapper;

import halcyon.clemncare.app.dto.ChildDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChildMapper {

    // Convert Entity to DTO
    public ChildDTO toDTO(Child child) {
        if (child == null) return null;

        ChildDTO dto = new ChildDTO();
        dto.setId(child.getId());
        dto.setFirstName(child.getFirstName());
        dto.setLastName(child.getLastName());
        dto.setDateOfBirth(child.getDateOfBirth());
        dto.setActive(child.isActive());
        dto.setNotes(child.getNotes());
        dto.setFamilyId(child.getFamily() != null ? child.getFamily().getId() : null);
        // dto.setName(child.getName()); // optional, for frontend
        // dto.setAge(child.getAge());   // optional, for frontend

        return dto;
    }

    // Convert DTO to Entity
    public Child toEntity(ChildDTO dto) {
        if (dto == null) return null;

        Child child = new Child();
        child.setId(dto.getId());
        child.setFirstName(dto.getFirstName());
        child.setLastName(dto.getLastName());
        child.setDateOfBirth(dto.getDateOfBirth());
        child.setActive(dto.isActive());
        child.setNotes(dto.getNotes());

        if (dto.getFamilyId() != null) {
            Family family = new Family();
            family.setId(dto.getFamilyId()); // stub only, avoids DB call
            child.setFamily(family);
        }

        return child;
    }

    // Convert list of entities to DTOs
    public List<ChildDTO> toDTOList(List<Child> children) {
        if (children == null) return null;
        return children.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Convert list of DTOs to entities
    public List<Child> toEntityList(List<ChildDTO> dtos) {
        if (dtos == null) return null;
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
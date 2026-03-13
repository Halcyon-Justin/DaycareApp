package halcyon.clemncare.app.mapper;

import halcyon.clemncare.app.dto.FamilyDTO;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.Invoice;
import halcyon.clemncare.app.model.HomeAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FamilyMapper {

    public FamilyDTO toDTO(Family family) {
        if (family == null) return null;

        FamilyDTO dto = new FamilyDTO();
        dto.setId(family.getId());
        dto.setAddressId(family.getAddress() != null ? family.getAddress().getId() : null);

        List<Long> childrenIds = family.getChildren() != null
                ? family.getChildren().stream().map(Child::getId).collect(Collectors.toList())
                : null;
        dto.setChildrenIds(childrenIds);

        List<Long> guardianIds = family.getGuardians() != null
                ? family.getGuardians().stream().map(Guardian::getId).collect(Collectors.toList())
                : null;
        dto.setGuardianIds(guardianIds);

        List<Long> invoiceIds = family.getInvoices() != null
                ? family.getInvoices().stream().map(Invoice::getId).collect(Collectors.toList())
                : null;
        dto.setInvoiceIds(invoiceIds);

        return dto;
    }

    public Family toEntity(FamilyDTO dto) {
        if (dto == null) return null;

        Family family = new Family();
        family.setId(dto.getId());

        if (dto.getAddressId() != null) {
            HomeAddress address = new HomeAddress();
            address.setId(dto.getAddressId());
            family.setAddress(address);
        }
        return family;
    }
}
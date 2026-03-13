package halcyon.clemncare.app.mapper;

import halcyon.clemncare.app.dto.InvoiceDTO;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null) return null;

        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setFamilyId(invoice.getFamily() != null ? invoice.getFamily().getId() : null);
        dto.setDueDate(invoice.getDueDate());
        dto.setAmountDue(invoice.getAmountDue());
        dto.setStatus(invoice.getStatus());

        return dto;
    }

    public Invoice toEntity(InvoiceDTO dto) {
        if (dto == null) return null;

        Invoice invoice = new Invoice();
        invoice.setId(dto.getId());
        invoice.setDueDate(dto.getDueDate());
        invoice.setAmountDue(dto.getAmountDue());
        invoice.setStatus(dto.getStatus());

        if (dto.getFamilyId() != null) {
            Family family = new Family();
            family.setId(dto.getFamilyId());
            invoice.setFamily(family);
        }

        return invoice;
    }
}
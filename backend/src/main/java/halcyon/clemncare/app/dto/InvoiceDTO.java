package halcyon.clemncare.app.dto;

import halcyon.clemncare.app.enums.InvoiceStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDTO {
    private Long id;
    private Long familyId;
    private LocalDate dueDate;
    private Long amountDue;
    private InvoiceStatus status;
}
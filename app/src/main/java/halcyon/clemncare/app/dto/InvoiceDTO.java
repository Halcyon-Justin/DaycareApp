package halcyon.clemncare.app.dto;

import java.time.LocalDate;

import halcyon.clemncare.app.enums.InvoiceStatus;
import halcyon.clemncare.app.model.Family;
import lombok.Data;

@Data
public class InvoiceDTO {
    private Long id;
    private Family family;
    private LocalDate dueDate;
    private Long amountDue;
    private InvoiceStatus status;
}

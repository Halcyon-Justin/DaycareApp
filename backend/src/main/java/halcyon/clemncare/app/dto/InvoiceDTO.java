package halcyon.clemncare.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import halcyon.clemncare.app.enums.InvoiceStatus;
import lombok.Data;

@Data
public class InvoiceDTO {

    private Long id;

    private Long familyId;

    private BigDecimal amount;

    private LocalDate dueDate;

    private LocalDate payDate;

    private InvoiceStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
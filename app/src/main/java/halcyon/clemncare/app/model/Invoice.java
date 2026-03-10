package halcyon.clemncare.app.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import halcyon.clemncare.app.enums.InvoiceStatus;
import halcyon.clemncare.app.listeners.EntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "invoice")
@EntityListeners(EntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class Invoice extends TimeStampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    private LocalDate dueDate;

    @Column(columnDefinition = "BIGINT default 0")
    private Long amountDue;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'UNPAID'")
    private InvoiceStatus status;
    
}

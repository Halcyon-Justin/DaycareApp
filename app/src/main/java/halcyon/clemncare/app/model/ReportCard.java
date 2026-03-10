package halcyon.clemncare.app.model;

import java.util.List;

import halcyon.clemncare.app.listeners.EntityListener;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "report_card")
@EntityListeners(EntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class ReportCard extends TimeStampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "child_id")
    private Long childId;

    @Column(columnDefinition = "BOOLEAN DEFAULT 'FALSE'")
    private boolean hasNapped;
    private String notes;

    @ElementCollection
    @CollectionTable(name = "sendTo_Child", joinColumns = @JoinColumn(name = "child_id"))
    @Column(name = "sendTo")
    private List<String> sendTo;

}

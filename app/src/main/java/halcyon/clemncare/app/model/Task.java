package halcyon.clemncare.app.model;

import halcyon.clemncare.app.enums.TaskStatus;
import halcyon.clemncare.app.listeners.EntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "task")
@EntityListeners(EntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class Task extends TimeStampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
}
